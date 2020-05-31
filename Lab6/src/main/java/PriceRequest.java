import java.io.Serializable;

public class PriceRequest implements Serializable {

    private int id;
    private String product;

    public PriceRequest(String product) {
        this.id = 1;
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public byte[] toByteArray(){
        return (product).getBytes();
    }

    public static PriceRequest parseFrom(byte[] arr) throws Exception{
        String content = new String(arr);
        return new PriceRequest(content);
    }
}
