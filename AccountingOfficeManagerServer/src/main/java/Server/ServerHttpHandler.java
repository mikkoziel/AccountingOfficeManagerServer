package Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class ServerHttpHandler implements HttpHandler {
    String context;

    public ServerHttpHandler(){
        this.context = "/admin-handler/";
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String htmlResponse=null;
        switch(httpExchange.getRequestMethod()){
            case "GET":
                htmlResponse = handleGetRequest(httpExchange);
                break;
            case "POST":
                htmlResponse = handlePostRequest(httpExchange);
                break;
            case "PUT":
                htmlResponse = handlePutRequest(httpExchange);
                break;
            case "DELETE":
                htmlResponse = handleDeleteRequest(httpExchange);
                break;
            case "OPTIONS":
                htmlResponse = handleOptionsRequest();
                break;
        }
        System.out.println(htmlResponse);
        assert htmlResponse != null;
        handleResponse(httpExchange, htmlResponse);
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        Headers reqHeaders = httpExchange.getRequestHeaders();
        int id = Integer.parseInt(reqHeaders.getFirst("id"));

        String uri = httpExchange.getRequestURI()
                .toString().replace(this.context,"");

        String htmlResponse="";
        if(uri.equals("schedules")){
//            htmlResponse = this.admin.getSchedules(id);
//        } else if(uri.matches("schedules/[0-9]+")){
//            htmlResponse = this.admin.getSchedule(id, uri.replace("schedules/", ""));
        }
        return htmlResponse;
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        Headers reqHeaders = httpExchange.getRequestHeaders();
        int id = Integer.parseInt(reqHeaders.getFirst("id"));

        String uri = httpExchange.getRequestURI()
                .toString().replace(this.context,"");

        String msg = this.parseMsg(httpExchange);

        String htmlResponse = "";
        if(uri.equals("schedules")){
//            htmlResponse = this.admin.postSchedule(msg, id);
//        } else if(uri.matches("schedules/[0-9]+")){
//            htmlResponse = this.admin.postClass(uri, msg);
        }
        return htmlResponse;
    }

    private String handlePutRequest(HttpExchange httpExchange) {
        Headers reqHeaders = httpExchange.getRequestHeaders();
        int id = Integer.parseInt(reqHeaders.getFirst("id"));

        String uri = httpExchange.getRequestURI()
                .toString().replace(this.context,"");

        String msg = this.parseMsg(httpExchange);

        String htmlResponse = "";
        if(uri.matches("schedules/[0-9]+")){
//            htmlResponse = this.admin.putSchedule(msg, id);
        } else if(uri.matches("classes/[0-9]+")){
//            htmlResponse = this.admin.putClass(uri.replace("classes/", ""), msg);
        }

        return htmlResponse;
    }

    private String handleDeleteRequest(HttpExchange httpExchange) {
        Headers reqHeaders = httpExchange.getRequestHeaders();
        String uri = httpExchange.getRequestURI()
                .toString().replace(this.context,"");

        int id = Integer.parseInt(reqHeaders.getFirst("id"));

        String htmlResponse = "";
        if(uri.matches("schedules/[0-9]+")) {
//            htmlResponse = this.admin.deleteSchedule(uri);
        } else if(uri.matches("classes/[0-9]+")) {
//            htmlResponse = this.admin.deleteClass(uri);
        }

        return htmlResponse;
    }

    private String handleOptionsRequest() {
        return "";
    }

    private void handleResponse(HttpExchange httpExchange, String htmlResponse)  throws  IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers","*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");

//        this.getLength(htmlResponse);
        // Create http response
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String parseMsg(HttpExchange httpExchange) {
        String message = null;
        try (InputStream in = httpExchange.getRequestBody()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder msgBuilder = new StringBuilder();
            int c;
            while ((c = br.read()) > -1) {
                msgBuilder.append((char) c);
            }
            message = msgBuilder.toString();
            System.out.println("Message: " + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}

