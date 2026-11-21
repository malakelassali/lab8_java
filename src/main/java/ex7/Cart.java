package ex7;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Cart {
    public enum Type {
        PHYSICAL, VIRTUAL
    }

    private final int id;
    private final Map<InventoryItem, Integer> products= new LinkedHashMap<>();
    private final LocalDate date;
    private final Type type;
    private boolean active= true;

    Cart(int id, Type type, LocalDate date) {
        this.id= id;
        this.type= Objects.requireNonNull(type);
        this.date= Objects.requireNonNull(date);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public Map<InventoryItem, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public boolean addItem(InventoryItem item, int quantity) {
        if (!active || item == null || !item.reserveItem(quantity)) {
            return false;
        }
        products.merge(item, quantity, Integer::sum);
        return true;
    }

    public boolean removeItem(InventoryItem item, int quantity) {
        int current= products.getOrDefault(item, 0);
        if (!active || quantity<1 || quantity>current) {
            return false;
        }
        if (!item.releaseItem(quantity)) {
            throw new IllegalStateException("Cart reservation is inconsistent");
        }
        if (quantity==current) {
            products.remove(item);
        } else {
            products.put(item, current - quantity);
        }
        return true;
    }

    public BigDecimal getTotal() {
        BigDecimal total= BigDecimal.ZERO;
        for (var entry : products.entrySet()) {
            total= total.add(entry.getKey().getSalesPrice()
                    .multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total;
    }

    public void printSalesSlip() {
        System.out.println("Cart " + id + " (" + type + ")");
        products.forEach((item, quantity) -> System.out.println(
                item.getProduct().name() + " x " + quantity + " = "
                        + item.getSalesPrice().multiply(BigDecimal.valueOf(quantity))));
        System.out.println("Total = " + getTotal());
    }

    void checkOut() {
        for (var entry : products.entrySet()) {
            if (entry.getKey().getQtyReserved()<entry.getValue()) {
                throw new IllegalStateException("Cart reservation is inconsistent");
            }
        }
        products.forEach((item, quantity) -> item.sellItem(quantity));
        products.clear();
        active= false;
    }

    void abandon() {
        products.forEach((item, quantity) -> item.releaseItem(quantity));
        products.clear();
        active= false;
    }
}

