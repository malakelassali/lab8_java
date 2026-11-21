package ex7;

import java.util.Objects;

public record Product(String sku, String name, String manufacturer, Category category) {
    public enum Category {
        PRODUCE, DAIRY, MEAT, BAKERY, DRINKS
    }

    public Product {
        if (sku==null || sku.isBlank() || name==null || name.isBlank() || manufacturer==null || manufacturer.isBlank()) {
            throw new IllegalArgumentException("Product details must not be blank");
        }
        Objects.requireNonNull(category);
    }
}

