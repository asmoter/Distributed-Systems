package Actors;

import Messages.DatabaseResponse;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.sql.*;

public class DbQueryHandler extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DatabaseResponse.class, request -> {
                    getSender().tell(updateDataBase(request.getProduct()), getSelf());
                })
                .matchAny(o -> log.info("Actors.Client: Received unknown message: " + o))
                .build();
    }

    private Integer updateDataBase(String productName) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try{
            Class.forName("org.sqlite.JDBC"); //
            connection = DriverManager.getConnection("jdbc:sqlite:products.db"); //
            connection.setAutoCommit(true); //
            statement = connection.createStatement(); //

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM PRODUCT_NAME WHERE name = '" + productName + "'"); //
            resultSet.next();
            int counter = resultSet.getInt("COUNT(*)");

            String query = "";

            if(counter == 0){
                query = "INSERT INTO PRODUCT_QUERY (PRODUCT_NAME, QUERY_COUNTER) VALUES ('" + productName + "', 1)";
                statement.executeUpdate(query);
            } else {
                query = "SELECT * FROM PRODUCT_QUERY WHERE PRODUCT_NAME='" + productName + "'";
                resultSet = statement.executeQuery(query);
                int currentNumber = resultSet.getInt("QUERY_COUNTER");

                query = "UPDATE PRODUCT_QUERY SET QUERY_COUNTER=" + (++currentNumber) + " WHERE PRODUCT_NAME='" + productName + "'";
                statement.executeUpdate(query);
            }

            query = "SELECT QUERY_COUNTER FROM PRODUCT_QUERY WHERE PRODUCT_NAME='" + productName + "'";
            resultSet = statement.executeQuery(query);

            return resultSet.getInt("QUERY_COUNTER");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }

        return null;
    }

}
