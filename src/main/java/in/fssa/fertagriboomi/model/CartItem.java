package in.fssa.fertagriboomi.model;

public class CartItem {
    private int productId;
    private int quantity;

    // Constructor to initialize productId and quantity
    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter and setter methods for productId
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter and setter methods for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
    @Override
    public String toString() {
        return "CartItem{" +
               "productId=" + productId +
               ", quantity=" + quantity +
               '}';
    }
}
