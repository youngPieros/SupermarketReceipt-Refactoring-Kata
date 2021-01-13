package dojo.supermarket.model.receipt;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantity;

import java.util.ArrayList;
import java.util.Objects;

public class ReceiptItem implements Comparable<ReceiptItem> {
    private final Product product;
    private double quantity;
    private ArrayList<Discount> discounts = new ArrayList<>();

    private ReceiptItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
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

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    public double getTotalPrice() {
        double price = getPurePrice();
        for(Discount discount: discounts) {
            price -= discount.getDiscountAmount();
        }
        return price;
    }

    public double getPurePrice() {
        return product.getPrice() * quantity;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem receiptItem = (ReceiptItem) o;
        return Double.compare(receiptItem.quantity, quantity) == 0 &&
                Objects.equals(product, receiptItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }


    @Override
    public int compareTo(ReceiptItem receiptItem) {
        return this.product.getName().compareTo(receiptItem.product.getName());
    }
}
