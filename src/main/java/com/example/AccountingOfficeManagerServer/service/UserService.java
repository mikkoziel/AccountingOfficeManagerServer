package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.configuration.RoleEnum;
import com.example.AccountingOfficeManagerServer.entity.model.*;
import com.example.AccountingOfficeManagerServer.entity.modelpack.ChangeRole;
import com.example.AccountingOfficeManagerServer.repository.ClientRepository;
import com.example.AccountingOfficeManagerServer.repository.EmployeeRepository;
import com.example.AccountingOfficeManagerServer.repository.RoleRepository;
import com.example.AccountingOfficeManagerServer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new ValidationException("Username exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void checkIfValidPassword(User user, String password) throws Exception{
        if(!passwordEncoder.matches(password, user.getPassword())){
            logger.info("Wrong");
            throw new Exception();
        }
        logger.info("Right");
    }

    public void changeUserPassword(User user, String password){
        String encodedPassword = passwordEncoder.encode(password).replace("{bcrypt}", "");
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
    }

    public void changeUserRole(Integer user_id, ChangeRole changeRole){
        User user = this.getUser(user_id);
        Role role = this.roleRepository.findById(changeRole.getRole_id()).get();
        user.cleanRoles();
        user.addRole(role);
    }

    public List<User> getParticipants(Integer user_id){
        User user = this.getUser(user_id);
        String user_role = user.getRoles().get(0).getName();
        String client_role = RoleEnum.CLIENT.toString();
//        logger.info(user_role);
//        logger.info(client_role);
        if(Objects.equals(user_role, client_role)){
            Client client = this.clientService.getClient(user_id);
            List<User> users = new ArrayList<>(client.getCompany().getUsers());
            users.remove(client);
            users.add(client.getEmployee());
            return users;
        } else {
            Employee employee = this.employeeService.getUser(user_id);
            List<User> users = new ArrayList<>();
            users.addAll(employee.getEmployees());
            users.addAll(employee.getClients());
            return users;
        }

    }
}
