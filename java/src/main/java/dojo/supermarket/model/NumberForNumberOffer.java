package dojo.supermarket.model;

import java.util.Map;

public class NumberForNumberOffer extends SingleProductOffer {

    private final int numberOfBoughtProducts;
    private final int numberOfPaidProducts;

    public NumberForNumberOffer(Product product, int numberOfPaidProducts, int numberOfBoughtProducts) {
        super(product);
        this.numberOfBoughtProducts = numberOfBoughtProducts;
        this.numberOfPaidProducts = numberOfPaidProducts;
    }

    @Override
    protected Discount calculateDiscount(Map<Product, Double> productQuantities) {
        int quantity = productQuantities.get(this.getProduct()).intValue();
        int quantityNumberWithDiscount = quantity / numberOfBoughtProducts;
        double discount = quantityNumberWithDiscount * (this.numberOfBoughtProducts - this.numberOfPaidProducts) * this.getProduct().getPrice();
        String description = numberOfBoughtProducts + " for " + numberOfPaidProducts;
        return Discount.createDiscount(this.getProduct(), description, discount);
    }

    @Override
    protected boolean isApplicableOffer(Map<Product, Double> productQuantities) {
        return productQuantities.get(getProduct()) >= numberOfBoughtProducts;
    }
}
