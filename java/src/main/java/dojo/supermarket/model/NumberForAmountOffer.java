package dojo.supermarket.model;

import java.util.Map;

public class NumberForAmountOffer extends AbstractOffer {

    private final Product product;
    private final int numberOfAmountOffer;
    private final double amount;

    public NumberForAmountOffer(Product product, int numberOfAmountOffer, double amount) {
        this.product = product;
        this.numberOfAmountOffer = numberOfAmountOffer;
        this.amount = amount;
    }

    @Override
    public Discount calculateDiscount(Map<Product, Double> productQuantities) {
        double quantityNumberWithDiscount = getIncludedProductDiscountNumber(productQuantities);
        double discount = quantityNumberWithDiscount * (product.getPrice() - this.amount / numberOfAmountOffer);
        return Discount.createDiscount(this.numberOfAmountOffer + " for " + this.amount, discount);
    }

    @Override
    public Double getIncludedProductDiscountNumber(Map<Product, Double> productQuantities) {
        int quantity = productQuantities.get(product).intValue();
        return (double) (quantity / numberOfAmountOffer * numberOfAmountOffer);
    }

}
