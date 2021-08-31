package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.configuration.StorageProperties;
import com.example.AccountingOfficeManagerServer.entity.exception.StorageException;
import com.example.AccountingOfficeManagerServer.entity.exception.StorageFileNotFoundException;
import com.example.AccountingOfficeManagerServer.entity.model.ClientCompany;
import com.example.AccountingOfficeManagerServer.entity.model.Document;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.repository.DocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CCService ccService;

    private final Path rootLocation;
    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    public DocumentService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public List<Document> listAllDocument() {
        return documentRepository.findAll();
    }

    public void saveDocument(Document document) {
        documentRepository.save(document);
    }

    public void saveDocumentWithFile(Document document, MultipartFile file) {
        User user = this.userService.getUser(document.getClient().getUser_id());
        ClientCompany cc = this.ccService.getClientCompany(user.getCompany().getCompany_id());
        document.setCompany(cc);
        String modifiedFileName = this.modifyFileName(file, document);
        String path = this.store(file, document, modifiedFileName);
        document.setPath(path);
        this.saveDocument(document);
    }

    public Document getDocument(Integer id) {
        return documentRepository.findById(id).get();
    }

    public void deleteDocument(Integer id) {
        documentRepository.deleteById(id);
    }

    public List<Document> listAllDocumentsForUser(Integer user_id) {return documentRepository.findByClientId(user_id);}

    public List<Document> listAllDocumentsForCompany(Integer company_id) {return documentRepository.findByCompanyId(company_id);}

    public String modifyFileName(MultipartFile file, Document document){
        String original = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmssS");
        String dateString = format.format(new Date());
        if(original != null) {
            String extension = original.substring(original.lastIndexOf(".") + 1);
            String orig = original.substring(0, original.lastIndexOf("."));
            return orig + "_" + document.getClient().getUser_id() + "_" + dateString + "." + extension;
        }
        return document.getClient().getUser_id() + "_" + dateString + ".pdf";
    }

    public String store(MultipartFile file, Document document, String modifiedFileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(Objects.requireNonNull(modifiedFileName)))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            this.setFileMetaData(destinationFile, document);
            return destinationFile.toString();
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    public void setFileMetaData(Path path, Document document) throws IOException {
        PDDocument pdfDoc = PDDocument.load(path.toFile());
        pdfDoc.getDocumentInformation().setCustomMetadataValue("transfer-date", new Date().toString());
        pdfDoc.getDocumentInformation().setCustomMetadataValue("transfer-client", String.valueOf(document.getClient().getUser_id()));
        pdfDoc.getDocumentInformation().setCustomMetadataValue("transfer-company", String.valueOf(document.getCompany().getCompany_id()));
        pdfDoc.save(path.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            if(!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
