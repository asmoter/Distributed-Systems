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

    public byte[] toByteArray(){
        return (product + ";" + queryCounter).getBytes();
    }

    public static DatabaseResponse parseFrom(byte[] arr) throws Exception{
        String content = new String(arr);
        String[] response = content.split(";");
        return new DatabaseResponse(response[0], Integer.parseInt(response[1]));
    }

}
