package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentDiscountOffer2 extends BundleProductOffer {

    public PercentDiscountOffer2(ArrayList<Product> products) {
        super(products);
    }

    @Override
    public List<Discount> calculateDiscounts(Map<Product, Double> productQuantities) {
        return null;
    }

    @Override
    protected boolean isApplicableOffer(Map<Product, Double> productQuantities) {
        return true;
    }
}
