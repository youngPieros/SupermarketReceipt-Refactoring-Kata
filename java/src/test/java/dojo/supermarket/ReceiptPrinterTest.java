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
        receipt.addProduct(toothbrush, 1);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void quantityTwo() {
        receipt.addProduct(toothbrush, 2);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void looseWeight() {
        receipt.addProduct(apples, 2.3);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void total() {
        receipt.addProduct(toothbrush, 1);
        receipt.addProduct(apples, 0.75);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void discounts() {
        receipt.addDiscount(new Discount(apples, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void printWholeReceipt() {
        receipt.addProduct(toothbrush, 1);
        receipt.addProduct(toothbrush, 2);
        receipt.addProduct(apples, 0.75);
        receipt.addDiscount(new Discount(toothbrush, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

}
