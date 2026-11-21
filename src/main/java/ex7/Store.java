package ex7;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class Store {
    private final Map<String, InventoryItem> inventory= new TreeMap<>();
    private final Map<Integer, Cart> carts= new LinkedHashMap<>();
    private final Map<Product.Category, Set<Product>> aisleInventory= new EnumMap<>(Product.Category.class);

    public void addInventoryItem(InventoryItem item) {
        Objects.requireNonNull(item);
        Product product= item.getProduct();
        if (inventory.putIfAbsent(product.sku(), item)!=null) {
            throw new IllegalArgumentException("SKU already exists");
        }
        aisleInventory.computeIfAbsent(product.category(), category ->
                new LinkedHashSet<>()).add(product);
    }

    public InventoryItem getInventoryItem(String sku) {
        return inventory.get(sku);
    }

    public Map<String, InventoryItem> getInventory() {
        return Collections.unmodifiableMap(inventory);
    }

    public List<Cart> getCarts() {
        return List.copyOf(carts.values());
    }

    public Cart manageStoreCarts(int id, Cart.Type type) {
        return manageStoreCarts(id, type, LocalDate.now());
    }

    public Cart manageStoreCarts(int id, Cart.Type type, LocalDate date) {
        if (carts.containsKey(id)) {
            throw new IllegalArgumentException("Cart ID already exists");
        }
        Cart cart= new Cart(id, type, date);
        carts.put(id, cart);
        return cart;
    }

    public boolean addItem(int cartId, String sku, int quantity) {
        Cart cart= carts.get(cartId);
        InventoryItem item= inventory.get(sku);
        return cart!=null && item!=null && cart.addItem(item, quantity);
    }

    public boolean removeItem(int cartId, String sku, int quantity) {
        Cart cart= carts.get(cartId);
        InventoryItem item= inventory.get(sku);
        return cart!=null && item!=null && cart.removeItem(item, quantity);
    }

    public BigDecimal checkOutCart(int cartId) {
        Cart cart= carts.get(cartId);
        if (cart==null) {
            throw new IllegalArgumentException("Unknown cart");
        }
        BigDecimal total= cart.getTotal();
        cart.printSalesSlip();
        cart.checkOut();
        carts.remove(cartId);
        return total;
    }

    public int abandonCarts() {
        LocalDate today= LocalDate.now();
        int previousSize= carts.size();
        carts.values().removeIf(cart -> {
            if (!cart.getDate().equals(today)) {
                cart.abandon();
                return true;
            }
            return false;
        });
        return previousSize-carts.size();
    }

    public List<Product> listProductsByCategory(Product.Category category) {
        List<Product> products= List.copyOf(
                aisleInventory.getOrDefault(category, Set.of()));
        products.forEach(product -> System.out.println(inventory.get(product.sku())));
        return products;
    }
}

