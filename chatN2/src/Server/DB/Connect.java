package Server.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {

    public static Connection c;
    public static Statement st;

    public static void setDB() throws Exception {
        String url = "jdbc:mysql://localhost:3306/Chat";

        String login = "root";
        String pass = "root";

        Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection(url, login, pass);
        st = c.createStatement();
    }
}
