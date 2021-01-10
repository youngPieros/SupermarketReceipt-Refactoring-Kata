package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void addProduct(ProductQuantity productQuantity) {
        for (ReceiptItem receiptItem: this.items)
            if (receiptItem.getProduct().equals(productQuantity.getProduct())) {
                receiptItem.addQuantity(productQuantity.getQuantity());
                return;
            }
        this.items.add(ReceiptItem.createFromProductQuantity(productQuantity));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Product product, Discount discount) {
        for (ReceiptItem receiptItem: items) {
            if (receiptItem.getProduct().equals(product)) {
                receiptItem.setDiscount(discount);
            }
        }
    }

}
