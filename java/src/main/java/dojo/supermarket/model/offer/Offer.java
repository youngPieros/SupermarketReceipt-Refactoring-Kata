package dojo.supermarket.model.offer;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;

import java.util.*;

public interface Offer {
    List<Discount> calculateDiscounts(Map<Product, Double> productQuantities);

    boolean isApplicable(Map<Product, Double> productQuantities);
}
