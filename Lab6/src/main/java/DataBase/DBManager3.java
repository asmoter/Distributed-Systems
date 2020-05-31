package DataBase;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager3 {

    private static String DbURL = "jdbc:sqlite:C:/SQLite/Akka.db";
    private static String tableName = "PRODUCT_QUERIES";
    private static Connection connection;

    public static Connection getConnection(){
        return connection;
    }

    public static void setConnection(){
        try{
            SQLiteConfig config = new SQLiteConfig();
            config.setOpenMode(SQLiteOpenMode.FULLMUTEX);
            connection = DriverManager.getConnection(DbURL, config.toProperties());
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createDatabase() {
        try{
            Connection connection = DriverManager.getConnection(DbURL);
            if(connection != null){
                createProductsTable(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createProductsTable(Connection connection) {

        String SQL = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (PRODUCT_NAME TEXT PRIMARY KEY NOT NULL, " +
                " QUERY_COUNTER INT NOT NULL)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
