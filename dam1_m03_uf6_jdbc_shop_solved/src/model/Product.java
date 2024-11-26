package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product") 
public class Product {

    private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts;

    private String badge;
    private String color;

    public static final double EXPIRATION_RATE = 0.60;

    public Product() {
        this.id = ++totalProducts; // Incrementa automáticamente el ID
        this.available = true;     // Valor predeterminado
    }

    public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
        this(); 
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice = new Amount(wholesalerPrice.getValue() * 2);
        this.stock = stock;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public Amount getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public static int getTotalProducts() {
        return totalProducts;
    }

    public static void setTotalProducts(int totalProducts) {
        Product.totalProducts = totalProducts;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void expire() {
        if (this.publicPrice != null) {
            double newValue = this.publicPrice.getValue() * EXPIRATION_RATE;
            this.publicPrice.setValue(newValue);
        }
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice=" + wholesalerPrice
                + ", available=" + available + ", stock=" + stock + "]";
    }
}
