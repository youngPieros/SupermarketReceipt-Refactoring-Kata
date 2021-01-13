package dojo.supermarket.model.product;

import java.util.Objects;

public class Product {
    private final String name;
    private final ProductUnit unit;
    private final double price;

    public Product(String name, double price, ProductUnit unit) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(this.name, product.name) &&
                this.unit == product.unit &&
                this.price == product.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, unit);
    }
}
