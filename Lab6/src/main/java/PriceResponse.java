import java.io.Serializable;

public class PriceResponse implements Serializable {

    private int id;
    private final int price;
    private String product;

    public PriceResponse(String product, int price) {
        this.id = 2;
        this.product = product;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceResponse{ price = " + price + ", product = " + product + "}";
    }

    public int getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public byte[] toByteArray(){
        return (product + ";" + price).getBytes();
    }

    public static PriceResponse parseFrom(byte[] arr) throws Exception{
        String content = new String(arr);
        String[] response = content.split(";");
        return new PriceResponse(response[0], Integer.parseInt(response[1]));
    }
}
