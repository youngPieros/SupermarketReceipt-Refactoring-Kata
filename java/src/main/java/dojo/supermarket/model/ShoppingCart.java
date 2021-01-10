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

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    public void addItemQuantity(Product product, double quantity) {
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;
                Discount discount = null;
                int x = 1;
                if (offer.offerType == SpecialOfferType.ThreeForTwo) {
                    x = 3;

                } else if (offer.offerType == SpecialOfferType.TwoForAmount) {
                    x = 2;
                    if (quantityAsInt >= 2) {
                        int intDivision = quantityAsInt / x;
                        double pricePerUnit = offer.argument * intDivision;
                        double theTotal = (quantityAsInt % 2) * unitPrice;
                        double total = pricePerUnit + theTotal;
                        double discountN = unitPrice * quantity - total;
                        discount = Discount.createDiscount("2 for " + offer.argument, -discountN);
                    }

                } if (offer.offerType == SpecialOfferType.FiveForAmount) {
                    x = 5;
                }
                int numberOfXs = quantityAsInt / x;
                if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
                    discount = Discount.createDiscount("3 for 2", -discountAmount);
                }
                if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
                    discount = Discount.createDiscount(offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
                }
                if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
                    discount = Discount.createDiscount(x + " for " + offer.argument, -discountTotal);
                }
                if (discount != null)
                    receipt.addDiscount(p, discount);
            }

        }
    }
}
