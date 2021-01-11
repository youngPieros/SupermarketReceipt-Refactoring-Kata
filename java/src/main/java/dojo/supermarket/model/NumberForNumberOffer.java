package dojo.supermarket.model;

import java.util.Map;

public class NumberForNumberOffer extends AbstractOffer {

    private final int numberOfBoughtProducts;
    private final int numberOfPaidProducts;
    private final Product product;

    public NumberForNumberOffer(Product product, int numberOfPaidProducts, int numberOfBoughtProducts) {
        this.product = product;
        this.numberOfBoughtProducts = numberOfBoughtProducts;
        this.numberOfPaidProducts = numberOfPaidProducts;
    }

    @Override
    public Discount calculateDiscount(Map<Product, Double> productQuantities) {
        double quantityNumberWithDiscount = getIncludedProductDiscountNumber(productQuantities);
        double discount = (quantityNumberWithDiscount / numberOfBoughtProducts) *
                (this.numberOfBoughtProducts - this.numberOfPaidProducts) * product.getPrice();
        String description = numberOfBoughtProducts + " for " + numberOfPaidProducts;
        return Discount.createDiscount(description, discount);
    }

    @Override
    public Double getIncludedProductDiscountNumber(Map<Product, Double> productQuantities) {
        int quantity = productQuantities.get(product).intValue();
        return (double) ((quantity / numberOfBoughtProducts) * numberOfBoughtProducts);
    }
}
