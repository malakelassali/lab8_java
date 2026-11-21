package ex7;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Product apples = new Product("P001", "Apples", "Local Farm", Product.Category.PRODUCE);
        Product milk = new Product("D001", "Milk", "Dairy Farm", Product.Category.DAIRY);

        store.addInventoryItem(new InventoryItem(apples, 10, 10, 3, new BigDecimal("2.50")));
        store.addInventoryItem(new InventoryItem(milk, 8, 6, 2, new BigDecimal("1.80")));
        store.listProductsByCategory(Product.Category.PRODUCE);
        store.listProductsByCategory(Product.Category.DAIRY);

        store.manageStoreCarts(1, Cart.Type.PHYSICAL);
        System.out.println("Add 8 apples = " + store.addItem(1, "P001", 8));
        System.out.println("Remove 1 apple = " + store.removeItem(1, "P001", 1));
        System.out.println("Add 2 milk = " + store.addItem(1, "D001", 2));
        System.out.println("Add too much milk = " + store.addItem(1, "D001", 20));
        System.out.println(store.getInventoryItem("P001"));
        store.checkOutCart(1);

        store.manageStoreCarts(2, Cart.Type.VIRTUAL, LocalDate.now().minusDays(1));
        store.manageStoreCarts(3, Cart.Type.PHYSICAL, LocalDate.now().minusDays(1));
        store.addItem(2, "P001", 2);
        store.addItem(3, "D001", 1);
        System.out.println("Abandoned carts = " + store.abandonCarts());

        store.getInventory().values().forEach(System.out::println);
        System.out.println("Active carts = " + store.getCarts().size());
    }
}

