package dojo.supermarket.model.offer.single;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;

import java.util.Map;

public class NumberForAmountOffer extends SingleProductOffer {

    private final int numberOfAmountOffer;
    private final double amount;

    public NumberForAmountOffer(Product product, int numberOfAmountOffer, double amount) {
        super(product);
        this.numberOfAmountOffer = numberOfAmountOffer;
        this.amount = amount;
    }

    @Override
    public Discount calculateDiscount(Map<Product, Double> productQuantities) {
        int quantity = productQuantities.get(this.getProduct()).intValue();
        int quantityNumberWithDiscount = (quantity / numberOfAmountOffer * numberOfAmountOffer);
        double discount = quantityNumberWithDiscount * (this.getProduct().getPrice() - this.amount / numberOfAmountOffer);
        return Discount.createDiscount(this.getProduct(), this.numberOfAmountOffer + " for " + this.amount, discount);
    }

    @Override
    protected boolean isApplicableOffer(Map<Product, Double> productQuantities) {
        return productQuantities.get(getProduct()) >= numberOfAmountOffer;
    }

}
