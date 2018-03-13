package Client.DB;


import Client.GUI.ClientLauncher;

import java.sql.ResultSet;


public class Send {
    private static ResultSet rs;

    public static void History() throws Exception {
        Connect.setDB();
        String SQL = "SELECT nickname, messege FROM `Client`";
        rs = Connect.st.executeQuery(SQL);
        while (rs.next()){
            String line = (rs.getString("nickname") + ": " + rs.getString("messege")+ "\n");
            ClientLauncher.ta.append(line);
        }
    }

}
