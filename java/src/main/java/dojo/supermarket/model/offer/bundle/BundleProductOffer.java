package dojo.supermarket.model.offer.bundle;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.AbstractOffer;
import dojo.supermarket.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BundleProductOffer extends AbstractOffer {

    private final ArrayList<Product> products;

    public BundleProductOffer(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public List<Discount> calculateDiscounts(Map<Product, Double> productQuantities) {
        Map<Product, Double> discountQuantities = getDiscountQuantities(productQuantities);
        ArrayList<Discount> discounts = calculateBundleDiscounts(productQuantities);
        for (Product product: products) {
            double currentQuantity = productQuantities.get(product);
            double discountQuantity = discountQuantities.get(product);
            productQuantities.put(product, currentQuantity - discountQuantity);
        }
        return discounts;
    }

    @Override
    public boolean isApplicable(Map<Product, Double> productQuantities) {
        for (Product product : getProducts())
            if (productQuantities.get(product) == null)
                return false;
        return isApplicableOffer(productQuantities);
    }

    protected abstract boolean isApplicableOffer(Map<Product, Double> productQuantities);

    protected abstract Map<Product, Double>  getDiscountQuantities(Map<Product, Double> productQuantities);

    protected abstract ArrayList<Discount> calculateBundleDiscounts(Map<Product, Double> productQuantities);
}
