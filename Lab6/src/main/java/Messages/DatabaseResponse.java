package Messages;

public class DatabaseResponse {

    private String product;
    private int queryCounter;

    public DatabaseResponse(String product, int queryCounter) {
        this.product = product;
        this.queryCounter = queryCounter;
    }

    public String getProduct() {
        return product;
    }

    public int getQueryCounter(){
        return queryCounter;
    }

}
