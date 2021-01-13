package dojo.supermarket.model.offer.bundle;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PercentDiscountOffer extends BundleProductOffer {

    private final double amountPercent;

    public PercentDiscountOffer(ArrayList<Product> products, double amountPercent) {
        super(products);
        this.amountPercent = amountPercent;
    }

    @Override
    public ArrayList<Discount> calculateBundleDiscounts(Map<Product, Double> productQuantities) {
        ArrayList<Product> products = getProducts();
        ArrayList<Discount> discounts = new ArrayList<>();
        int quantityNumberOfDiscountProduct = getDiscountQuantities(productQuantities).get(products.get(0)).intValue();
        for (Product product: products) {
            String description = this.amountPercent + "% off";
            double discount = quantityNumberOfDiscountProduct * product.getPrice()  * amountPercent / 100;
            discounts.add(Discount.createDiscount(product, description, discount));
        }
        return discounts;
    }

    @Override
    protected boolean isApplicableOffer(Map<Product, Double> productQuantities) {
        return true;
    }

    @Override
    protected Map<Product, Double> getDiscountQuantities(Map<Product, Double> productQuantities) {
        Map<Product, Double> discountQuantities = new HashMap<>();
        ArrayList<Product> products = getProducts();
        int quantityNumberOfDiscountProduct = productQuantities.get(products.get(0)).intValue();
        for (Product product: products) {
            int quantityProduct = productQuantities.get(product).intValue();
            if (quantityNumberOfDiscountProduct > quantityProduct)
                quantityNumberOfDiscountProduct = quantityProduct;
        }
        for (Product product: products)
            discountQuantities.put(product, (double) quantityNumberOfDiscountProduct);
        return discountQuantities;
    }
}
