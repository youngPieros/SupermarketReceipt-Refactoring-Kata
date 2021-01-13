package dojo.supermarket.model.offer;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;

import java.util.*;

public interface Offer {
    public List<Discount> calculateDiscounts(Map<Product, Double> productQuantities);

    public boolean isApplicable(Map<Product, Double> productQuantities);
}
