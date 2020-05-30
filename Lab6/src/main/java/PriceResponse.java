
public class PriceResponse {

    private final int price;
    private String product;

    public PriceResponse(String product, int price) {
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
}
