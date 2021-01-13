package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private final Map<Product, Double> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        List<ProductQuantity> productQuantities = new ArrayList<>();
        for (Product product: this.productQuantities.keySet()) {
            productQuantities.add(new ProductQuantity(product, this.productQuantities.get(product)));
        }
        return productQuantities;
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    public void addItemQuantity(Product product, double quantity) {
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers) {
        for (Product product: offers.keySet()) {
            Offer offer = offers.get(product);
            if (!offer.isApplicable(productQuantities))
                continue;
            for (Discount discount: offer.calculateDiscounts(productQuantities))
                receipt.addDiscount(discount.getProduct(), discount);
        }
    }
}
