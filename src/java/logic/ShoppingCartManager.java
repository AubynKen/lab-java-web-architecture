package logic;

import jakarta.annotation.PostConstruct;

import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import model.Product;
import model.ShoppingCartItem;

/**
 * The ShoppingCartManager manages the items in the shopping cart.
 */
@Named
@SessionScoped
public class ShoppingCartManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // good practice

    /**
     * Items that are currently in the shopping cart
     */
    List<ShoppingCartItem> items;

    @PostConstruct
    public void initShoppingCartManager() {
        items = new ArrayList<>();
    }

    public ShoppingCartManager() {
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public String addItem(Product product) {
        // null safety
        if (product == null) {
            return "";
        }

        // check if the product is already in the cart, and add quantity if it is
        for (var item: items) {
            if (product.getId().equals(item.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return "goToShoppingCart";
            }
        }

        // if the product is not in the cart, add it
        var item = new ShoppingCartItem();
        item.setId(product.getId());
        item.setQuantity(1);
        item.setProduct(product);
        items.add(item);
        return "goToShoppingCart";
    }
}
