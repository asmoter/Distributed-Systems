package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    public static void main( String[] args ) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProductsDB.db");

            Statement statement = connection.createStatement();

            String query = "create table if not exists Products " +
                    "(ProductName varchar(256) primary key not null, " +
                    "QueryCounter int not null)";

            statement.executeUpdate(query);
            statement.close();
            System.out.println("Succesfully created database");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
