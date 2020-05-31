package Messages;

import java.io.Serializable;

public class PriceResponse implements Serializable {

    private int requestID;
    private String clientID;
    private final int price;
    private String product;

    public PriceResponse(String clientID, int requestID, String product, int price) {
        this.clientID = clientID;
        this.requestID = requestID;
        this.product = product;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Messages.PriceResponse{ price = " + price + ", product = " + product + "}";
    }

    public String getClientID(){
        return clientID;
    }

    public int getRequestID(){
        return requestID;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public byte[] toByteArray(){
        return (clientID + ";" + requestID + ";" + product + ";" + price).getBytes();
    }

    public static PriceResponse parseFrom(byte[] arr) throws Exception{
        String content = new String(arr);
        String[] response = content.split(";");
        return new PriceResponse(response[0], Integer.parseInt(response[1]), response[2], Integer.parseInt(response[3]));
    }
}
