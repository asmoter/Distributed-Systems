package Actors;

import Messages.DatabaseResponse;
import Messages.PriceRequest;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.sql.*;

public class DbQueryHandler extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    getSender().tell(updateDataBase(request.getProduct()), getSelf());
                })
                .matchAny(o -> log.info("Client: Received unknown message: " + o))
                .build();
    }

    private DatabaseResponse updateDataBase(String productName) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProductsDB.db");
            connection.setAutoCommit(true); //
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Products WHERE ProductName = '" + productName + "'"); //
            resultSet.next();
            int counter = resultSet.getInt("COUNT(*)");

            String query;

            if(counter == 0){
                query = "INSERT INTO Products (ProductName, QueryCounter) VALUES ('" + productName + "', 1)";
                statement.executeUpdate(query);
            } else {
                query = "SELECT * FROM Products WHERE ProductName ='" + productName + "'";
                resultSet = statement.executeQuery(query);
                int currentNumber = resultSet.getInt("QueryCounter");

                query = "UPDATE Products SET QueryCounter =" + (++currentNumber) + " WHERE ProductName ='" + productName + "'";
                statement.executeUpdate(query);
            }

            query = "SELECT QueryCounter FROM Products WHERE ProductName ='" + productName + "'";
            resultSet = statement.executeQuery(query);

            return new DatabaseResponse(productName, resultSet.getInt("QueryCounter"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
        return null;
    }
}
