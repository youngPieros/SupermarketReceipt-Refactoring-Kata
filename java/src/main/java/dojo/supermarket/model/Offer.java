package dojo.supermarket.model;
import java.util.*;

public interface Offer {
    List<Discount> calculateDiscounts(Map<Product, Double> productQuantities);

    boolean isApplicable(Map<Product, Double> productQuantities);
}
