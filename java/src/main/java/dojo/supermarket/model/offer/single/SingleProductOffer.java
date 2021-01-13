package dojo.supermarket.model.offer.single;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.AbstractOffer;
import dojo.supermarket.model.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class SingleProductOffer extends AbstractOffer {

    private final Product product;

    public SingleProductOffer(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public List<Discount> calculateDiscounts(Map<Product, Double> productQuantities) {
        Discount discount = calculateDiscount(productQuantities);
        return new ArrayList<>(Collections.singleton(discount));
    }

    @Override
    public boolean isApplicable(Map<Product, Double> productQuantities) {
        if (productQuantities.get(product) == null)
            return false;
        return isApplicableOffer(productQuantities);
    }

    protected abstract Discount calculateDiscount(Map<Product, Double> productQuantities);

    protected abstract boolean isApplicableOffer(Map<Product, Double> productQuantities);
}
