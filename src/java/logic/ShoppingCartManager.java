package logic;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import model.ShoppingCartItem;

@Named
@SessionScoped
public class ShoppingCartManager implements Serializable {
    
    private static final long serialVersionUID = 1L; // good practice
    
    List<ShoppingCartItem> items;
    
    @PostConstruct
    public void initShoppingCartManager() {
        items = new ArrayList();
        
        
    }
    
    public ShoppingCartManager() {
    }
    
    public List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }
}
