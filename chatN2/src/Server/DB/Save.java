package Server.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Save {

    public static void save(String sender, String messege) throws Exception {
        Connect.setDB();
        String SQL ="INSERT INTO `Client` (`nickname`, `messege`) VALUES ('"+sender+"', '"+messege+"');";
        Connect.st.executeUpdate(SQL);
    }


}
