package dojo.supermarket;

import dojo.supermarket.model.*;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ReceiptPrinterTest {

    Product toothbrush = new Product("toothbrush", 0.99, ProductUnit.Each);
    Product apples = new Product("apples", 1.99, ProductUnit.Kilo);
    Receipt receipt = new Receipt();

    @Test
    public void oneLineItem() {
        receipt.addProduct(new ProductQuantity(toothbrush, 1));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void quantityTwo() {
        receipt.addProduct(new ProductQuantity(toothbrush, 2));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void looseWeight() {
        receipt.addProduct(new ProductQuantity(apples, 2.3));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void total() {
        receipt.addProduct(new ProductQuantity(toothbrush, 1));
        receipt.addProduct(new ProductQuantity(apples, 0.75));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void discounts() {
        receipt.addDiscount(new Discount(apples, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void printWholeReceipt() {
        receipt.addProduct(new ProductQuantity(toothbrush, 1));
        receipt.addProduct(new ProductQuantity(toothbrush, 2));
        receipt.addProduct(new ProductQuantity(apples, 0.75));
        receipt.addDiscount(new Discount(toothbrush, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

}
