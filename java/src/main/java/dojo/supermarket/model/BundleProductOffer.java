package dojo.supermarket.model;

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
    public abstract List<Discount> calculateDiscounts(Map<Product, Double> productQuantities);

    @Override
    public boolean isApplicable(Map<Product, Double> productQuantities) {
        for (Product product : getProducts())
            if (productQuantities.get(product) == null)
                return false;
        return isApplicableOffer(productQuantities);
    }

    protected abstract boolean isApplicableOffer(Map<Product, Double> productQuantities);
}
