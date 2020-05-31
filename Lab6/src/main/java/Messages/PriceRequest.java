package Messages;

import java.io.Serializable;

public class PriceRequest implements Serializable {

    private int requestID;
    private String clientID;
    private String product;

    public PriceRequest(String clientID, int requestID, String product) {
        this.clientID = clientID;
        this.requestID = requestID;
        this.product = product;
    }

    public String getClientID(){
        return clientID;
    }

    public int getRequestID(){
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getProduct() {
        return product;
    }

    public byte[] toByteArray(){
        return (clientID + ";" + requestID + ";" + product).getBytes();
    }

    public static PriceRequest parseFrom(byte[] arr) throws Exception{
        String content = new String(arr);
        String[] request = content.split(";");
        return new PriceRequest(request[0], Integer.parseInt(request[1]), request[2]);
    }
}
