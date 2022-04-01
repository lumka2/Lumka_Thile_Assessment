package Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_Connection {
    private static int Recnum = 1;

    public ResultSet ConnectAndQuerySQL(String sDBURL, String sUserName, String sPassword, String sQuery) {
        //ResultSet rs1=null;
        ResultSet rs = null;
        try {

            String dbURL = sDBURL;
            String user = sUserName;
            String pass = sPassword;
            java.sql.Connection conn = DriverManager.getConnection(dbURL, user, pass);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int rowCount(ResultSet resultset) throws Exception {
        int count = 0;
        resultset.last();
        count = resultset.getRow();
        resultset.beforeFirst();
        return count;
    }
}

