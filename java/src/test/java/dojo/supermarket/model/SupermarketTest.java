package dojo.supermarket.model;

import dojo.supermarket.ReceiptPrinter;
import dojo.supermarket.model.offer.single.NumberForAmountOffer;
import dojo.supermarket.model.offer.single.NumberForNumberOffer;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.single.PercentDiscountOffer;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnit;
import dojo.supermarket.model.receipt.Receipt;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SupermarketTest {
    private SupermarketCatalog catalog;
    private Teller teller;
    private ShoppingCart theCart;
    private Product toothbrush;
    private Product rice;
    private Product apples;
    private Product cherryTomatoes;

    @BeforeEach
    public void setUp() {
        catalog = new FakeCatalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart();

        toothbrush = new Product("toothbrush", 0.99, ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        rice = new Product("rice", 2.99, ProductUnit.Each);
        catalog.addProduct(rice, 2.99);
        apples = new Product("apples", 1.99, ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        cherryTomatoes = new Product("cherry tomato box", 0.69, ProductUnit.Each);
        catalog.addProduct(cherryTomatoes, 0.69);

    }

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void one_normal_item() {
        theCart.addItem(toothbrush);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void two_normal_items() {
        theCart.addItem(toothbrush);
        theCart.addItem(rice);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void buy_two_get_one_free() {
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        Offer offer = new NumberForNumberOffer(toothbrush, 2, 3);
        teller.addSpecialOffer(offer, toothbrush);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void buy_two_get_one_free_but_insufficient_in_basket() {
        theCart.addItem(toothbrush);
        Offer offer = new NumberForNumberOffer(toothbrush, 2, 3);
        teller.addSpecialOffer(offer, toothbrush);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }
    @Test
    public void buy_five_get_one_free() {
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
        Offer offer = new NumberForNumberOffer(toothbrush, 2, 3);
        teller.addSpecialOffer(offer, toothbrush);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void loose_weight_product() {
        theCart.addItemQuantity(apples, .5);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void percent_discount() {
        theCart.addItem(rice);
        Offer offer = new PercentDiscountOffer(rice, 10.0);
        teller.addSpecialOffer(offer, rice);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void xForY_discount() {
        theCart.addItem(cherryTomatoes);
        theCart.addItem(cherryTomatoes);
        Offer offer = new NumberForAmountOffer(cherryTomatoes, 2, 0.99);
        teller.addSpecialOffer(offer, cherryTomatoes);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void xForY_discount_with_insufficient_in_basket() {
        theCart.addItem(cherryTomatoes);
        Offer offer = new NumberForAmountOffer(cherryTomatoes, 2, 0.99);
        teller.addSpecialOffer(offer, cherryTomatoes);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void FiveForY_discount() {
        theCart.addItemQuantity(apples, 5);
        Offer offer = new NumberForAmountOffer(apples, 5, 6.99);
        teller.addSpecialOffer(offer, apples);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void FiveForY_discount_withSix() {
        theCart.addItemQuantity(apples, 6);
        Offer offer = new NumberForAmountOffer(apples, 5, 5.99);
        teller.addSpecialOffer(offer, apples);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void FiveForY_discount_withSixteen() {
        theCart.addItemQuantity(apples, 16);
        Offer offer = new NumberForAmountOffer(apples, 5, 7.99);
        teller.addSpecialOffer(offer, apples);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void FiveForY_discount_withFour() {
        theCart.addItemQuantity(apples, 4);
        Offer offer = new NumberForAmountOffer(apples, 5, 8.99);
        teller.addSpecialOffer(offer, apples);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }
}
