package Actors;

import Messages.DatabaseResponse;
import Messages.PriceRequest;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbHandler extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private Connection connection;

    @Override
    public Receive createReceive() {

        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    Statement statement = connection.createStatement();
                    String productName = request.getProduct();
                    String query = "SELECT QueryCounter FROM Products WHERE ProductName = '" + productName + "'";
                    ResultSet resultSet = statement.executeQuery(query);

                    int requestCount;

                    if(resultSet.isBeforeFirst()) {
                        requestCount = resultSet.getInt("QueryCounter") + 1;
                        query = "UPDATE Products SET QueryCounter = " + requestCount + " WHERE ProductName='" + productName + "';";
                        statement.executeUpdate(query);
                    } else {
                        query = "INSERT INTO Products (ProductName, QueryCounter) values ('" + productName + "', 1);";
                        statement.executeUpdate(query);
                        requestCount = 1;
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                    DatabaseResponse response = new DatabaseResponse(productName, requestCount);
                    getSender().tell(response, getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    @Override
    public void preStart() throws Exception {
        Class.forName("org.sqlite.JDBC");
        SQLiteConfig config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.FULLMUTEX);
        connection = DriverManager.getConnection("jdbc:sqlite:ProductsDB.db", config.toProperties());
        connection.setAutoCommit(true);
    }
}
