

package blooddonorinfo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad Rakibul Haider
 */
public class Config {
    protected Connection connection = null;
    protected ResultSet resultSet   = null;
    protected Statement statement   = null;

    Config(){
        try {
            Class.forName("org.sqlite.JDBC");
            String homeDir      =   System.getProperty("user.dir");
            String dbFileName   =   homeDir + File.separator + "blooddonor.sqlite";
            String jdbcURL      =   "jdbc:sqlite:" + dbFileName;
            connection          =   DriverManager.getConnection(jdbcURL);
            statement           =   connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean endConnection(){
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean closeStatement(){
        try {
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
