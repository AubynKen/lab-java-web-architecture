package logic;

import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
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

    /**
     * Products that are currently sold in the store and shown in the catalogue
     */
    List<Product> products;


    /**
     * Index for the productId -> product correspondence checkup in O(1)
     */
    Map<Integer, Product> index;

    /* for form submission */
    private Integer id;
    private String name;
    private Double price;
    private String imageURI; // for displaying the product
    private String description;

    public CatalogueManager() {
    }

    /**
     * Shorthand for adding a product into list of products and
     * @param product
     */
    private void addProduct(Product product) {
        products.add(product);
        index.put(product.getId(), product);
    }

    @PostConstruct
    public void initCatalogue() {
        final int PRODUCT_COUNT = 5;

        // we use an ArrayList for O(1) insert and low overhead
        this.products = new ArrayList<>(PRODUCT_COUNT); // pre-allocate 5 slots
        this.index = new HashMap<>();

        var images = new String[]{
            "https://cdn-icons-png.flaticon.com/512/644/644458.png",
            "https://cdn-icons-png.flaticon.com/512/1046/1046751.png",
            "https://cdn-icons-png.flaticon.com/256/189/189561.png",
            "https://cdn-icons-png.flaticon.com/256/6190/6190871.png",
            "https://cdn-icons-png.flaticon.com/512/3330/3330314.png"
        };

        var names = new String[]{"iPhone 17", "Roasted Chicken", "Dell XPS 15",
            "Beats by Dre Headphone", "The Great Gatsby"};

        var prices = new Double[]{1088d, 5d, 1200d, 300d, 15d};

        var descriptions = new String[]{
            "The iPhone 17 is the latest iteration of Apple's iconic smartphone series. With cutting-edge technology and sleek design, it offers a seamless user experience, advanced camera capabilities, and powerful performance.",
            "Indulge in the savory delight of our perfectly roasted chicken. Tender, juicy, and bursting with flavor, this classic dish is sure to satisfy your cravings and leave you wanting more.",
            "The Dell XPS 15 is a powerhouse laptop designed for professionals and creatives alike. Featuring a stunning display, blazing-fast performance, and premium build quality, it's the perfect companion for productivity and entertainment on the go.",
            "Immerse yourself in music like never before with Beats by Dre Headphones. Offering unparalleled sound quality, stylish design, and comfortable fit, these headphones elevate your listening experience to new heights.",
            "Dive into the glamorous world of the roaring twenties with F. Scott Fitzgerald's timeless classic, The Great Gatsby. Set against the backdrop of lavish parties and societal upheaval, this captivating novel explores themes of love, ambition, and the American Dream."
        };

        // in case we modify anything and the array lengths are not aligned
        assert images.length == PRODUCT_COUNT;
        assert names.length == PRODUCT_COUNT;
        assert prices.length == PRODUCT_COUNT;
        assert descriptions.length == PRODUCT_COUNT;

        for (int i = 0; i < PRODUCT_COUNT; ++i)
        {
            var item = new Product();
            item.setId(i);
            item.setImageURI(images[i]);
            item.setName(names[i]);
            item.setPrice(prices[i]);
            item.setDescription(descriptions[i]);
            addProduct(item);
        }
    }

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

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Creates a new product and adds it to the list of products
     */
    public String createProduct() {
        var product = new Product();
        product.setDescription(description);
        product.setId(id);
        product.setImageURI(imageURI);
        product.setName(name);
        product.setPrice(price);
        addProduct(product);
        return "goToCatalogue";
    }
}
