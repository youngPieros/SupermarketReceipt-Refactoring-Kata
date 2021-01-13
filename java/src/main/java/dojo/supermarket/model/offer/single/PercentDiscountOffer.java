package dojo.supermarket.model.offer.single;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;

import java.util.Map;

public class PercentDiscountOffer extends SingleProductOffer {

    private final double amountPercent;

    public PercentDiscountOffer(Product product, double amountPercent) {
        super(product);
        this.amountPercent = amountPercent;
    }

    @Override
    protected Discount calculateDiscount(Map<Product, Double> productQuantities) {
        double quantityNumberWithDiscount = productQuantities.get(getProduct());
        double discount = quantityNumberWithDiscount * this.getProduct().getPrice() * amountPercent / 100;
        String description = this.amountPercent + "% off";
        return Discount.createDiscount(this.getProduct(), description, discount);
    }

    @Override
    protected boolean isApplicableOffer(Map<Product, Double> productQuantities) {
        return true;
    }
}
