package dojo.supermarket.model;

public class Discount {
    private final String description;
    private final double discountAmount;
    private final Product product;

    private Discount(Product product, String description, double discountAmount) {
        this.description = description;
        this.discountAmount = discountAmount;
        this.product = product;
    }

    public static Discount createDiscount(Product product, String description, double discountAmount) {
        return new Discount(product, description, discountAmount);
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return product;
    }
}
