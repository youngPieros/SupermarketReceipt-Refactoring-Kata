package dojo.supermarket.model;
import java.util.*;

public interface Offer {
    Discount calculateDiscount(Map<Product, Double> productQuantities);

    Double getIncludedProductDiscountNumber(Map<Product, Double> productQuantities);
}
