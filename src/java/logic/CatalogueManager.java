package logic;

import java.io.Serial;
import java.util.List;
import java.util.ArrayList;

import facade.ProductFacade;
import jakarta.ejb.EJB;
import model.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * The CatalogueManager manages items sold in the store.
 */
@Named
@ApplicationScoped
public class CatalogueManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EJB
    private ProductFacade productFacade;

    /**
     * Products that are currently sold in the store and shown in the catalogue
     */
    List<Product> products;

    @PostConstruct
    public void initCatalog(){
        products.addAll(productFacade.findAll());
    }

    /**
     * Index for the productId -> product correspondence checkup in O(1)
     */
    Map<Integer, Product> index;

    /* for form submission */
    private Integer id;
    private String name;
    private Double price;

    public CatalogueManager() {
    }

    /**
     * Shorthand for adding a product into list of products and
     * @param product
     */
    private void addProduct(Product product) {
        products.add(product);
        index.put(product.getId(), product);
        productFacade.create(product);
    }

//    @PostConstruct
//    public void initCatalogue() {
//        final int PRODUCT_COUNT = 5;
//
//        // we use an ArrayList for O(1) insert and low overhead
//        this.products = new ArrayList<>(PRODUCT_COUNT); // pre-allocate 5 slots
//        this.index = new HashMap<>();
//
//        var images = new String[]{
//            "https://cdn-icons-png.flaticon.com/512/644/644458.png",
//            "https://cdn-icons-png.flaticon.com/512/1046/1046751.png",
//            "https://cdn-icons-png.flaticon.com/256/189/189561.png",
//            "https://cdn-icons-png.flaticon.com/256/6190/6190871.png",
//            "https://cdn-icons-png.flaticon.com/512/3330/3330314.png"
//        };
//
//        var names = new String[]{"iPhone 17", "Roasted Chicken", "Dell XPS 15",
//            "Beats by Dre Headphone", "The Great Gatsby"};
//
//        var prices = new Double[]{1088d, 5d, 1200d, 300d, 15d};
//
//        // in case we modify anything and the array lengths are not aligned
//        assert images.length == PRODUCT_COUNT;
//        assert names.length == PRODUCT_COUNT;
//        assert prices.length == PRODUCT_COUNT;
//
//        for (int i = 0; i < PRODUCT_COUNT; ++i)
//        {
//            var item = new Product();
//            item.setId(i);
//            item.setName(names[i]);
//            item.setPrice(prices[i]);
//            addProduct(item);
//        }
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    /**
     * Creates a new product and adds it to the list of products
     */
    public String createProduct() {
        var product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        addProduct(product);
        return "goToCatalogue";
    }
}
