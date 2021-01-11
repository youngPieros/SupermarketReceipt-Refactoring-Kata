package dojo.supermarket.model;

import java.util.Map;

public class PercentDiscountOffer extends AbstractOffer {

    private final Product product;
    private final double amountPercent;

    public PercentDiscountOffer(Product product, double amountPercent) {
        this.product = product;
        this.amountPercent = amountPercent;
    }


    @Override
    public Discount calculateDiscount(Map<Product, Double> productQuantities) {
        double quantityNumberWithDiscount = getIncludedProductDiscountNumber(productQuantities);
        double discount = quantityNumberWithDiscount * product.getPrice() * amountPercent / 100;
        String description = this.amountPercent + "% off";
        return Discount.createDiscount(description, discount);
    }

    @Override
    public Double getIncludedProductDiscountNumber(Map<Product, Double> productQuantities) {
        return productQuantities.get(product);
    }
}
