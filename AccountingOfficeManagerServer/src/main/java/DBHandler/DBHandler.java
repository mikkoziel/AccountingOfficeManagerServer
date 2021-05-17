package DBHandler;

//import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {
    private Connection conn;
    private final String url;
    private final String user;
    private final String password;
    private final String driverClass;


    public DBHandler() {
        this.url = "jdbc:mysql://mysql.agh.edu.pl:3306" +
                "/" +
                "mkoziel1"; // +
//                "?characterEncoding=utf8";
        this.user = "mkoziel1";
        this.password = "NNY0J0kYBP6TmSUi";
        this.driverClass = "com.mysql.cj.jdbc.Driver";

        this.connectToDB();

    }

    public void connectToDB() {
        try {
            Class.forName(this.driverClass);
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}