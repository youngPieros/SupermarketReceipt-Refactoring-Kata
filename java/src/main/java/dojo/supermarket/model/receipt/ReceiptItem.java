package dojo.supermarket.model.receipt;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantity;

import java.util.Objects;

public class ReceiptItem {
    private final Product product;
    private double quantity;
    private Discount discount;

    private ReceiptItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
        this.discount = null;
    }

    public static ReceiptItem createFromProductQuantity(ProductQuantity productQuantity) {
        return new ReceiptItem(productQuantity.getProduct(), productQuantity.getQuantity());
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    public double getTotalPrice() {
        double price = getPurePrice();
        if (discount != null) {
            price -= discount.getDiscountAmount();
        }
        return price;
    }

    public double getPurePrice() {
        return product.getPrice() * quantity;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

}
