package dojo.supermarket.model;

public class Discount {
    private final String description;
    private final double discountAmount;

    private Discount(String description, double discountAmount) {
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public static Discount createDiscount(String description, double discountAmount) {
        return new Discount(description, discountAmount);
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

}
