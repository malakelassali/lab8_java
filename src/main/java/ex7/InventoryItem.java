package ex7;

import java.math.BigDecimal;
import java.util.Objects;

public class InventoryItem {
    private final Product product;
    private int qtyTotal;
    private int qtyReserved;
    private final int qtyReorder;
    private final int qtyLow;
    private final BigDecimal salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReorder, int qtyLow, BigDecimal salesPrice) {
        this.product= Objects.requireNonNull(product);
        this.salesPrice= Objects.requireNonNull(salesPrice);
        if (qtyTotal<0 || qtyReorder<1 || qtyLow<0 || qtyLow>Integer.MAX_VALUE-qtyReorder || salesPrice.signum()<0) {
            throw new IllegalArgumentException("Invalid stock quantities or price");
        }
        this.qtyTotal= qtyTotal;
        this.qtyReorder= qtyReorder;
        this.qtyLow= qtyLow;
    }


    public Product getProduct() {
        return product;
    }

    public int getQtyTotal() {
        return qtyTotal;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public int getQtyAvailable() {
        return qtyTotal-qtyReserved;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public boolean reserveItem(int quantity) {
        if (quantity<1 || quantity>getQtyAvailable()) {
            return false;
        }
        qtyReserved+= quantity;
        return true;
    }

    public boolean releaseItem(int quantity) {
        if (quantity<1 || quantity>qtyReserved) {
            return false;
        }
        qtyReserved-= quantity;
        return true;
    }

    public boolean sellItem(int quantity) {
        if (quantity<1 || quantity>qtyReserved) {
            return false;
        }
        qtyReserved-= quantity;
        qtyTotal-= quantity;
        if (qtyTotal<=qtyLow) {
            placeInventoryOrder();
        }
        return true;
    }

    public void placeInventoryOrder() {
        qtyTotal= Math.addExact(qtyTotal, qtyReorder);
        System.out.println("Restocked " + product.name() + " by " + qtyReorder);
    }

    @Override
    public String toString() {
        return "%s | price %s | total %d | reserved %d | available %d"
                .formatted(product.name(), salesPrice, qtyTotal, qtyReserved, getQtyAvailable());
    }
}

