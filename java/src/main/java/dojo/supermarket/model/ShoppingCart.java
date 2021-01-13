package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantity;
import dojo.supermarket.model.receipt.Receipt;

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

    void handleOffers(Receipt receipt, ArrayList<Offer> offers) {
        for (Offer offer: offers) {
            if (!offer.isApplicable(productQuantities))
                continue;
            for (Discount discount: offer.calculateDiscounts(productQuantities))
                receipt.addDiscount(discount.getProduct(), discount);
        }
        Collections.sort(receipt.getItems());
    }
}
