package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

    public final static double EXPIRATION_RATE = 0.60;

    // Constructor sin parámetros
    public Product() {
        this.id = ++totalProducts;  // Incrementa el id único para cada producto
        this.available = true;      // Valor por defecto
    }

    // Constructor con parámetros
    public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
        this();  // Llama al constructor sin parámetros para inicializar id y available
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice = new Amount(wholesalerPrice.getValue() * 2);
        this.stock = stock;
    }

    // Anotaciones JAXB para los elementos a serializar
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Amount getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    @XmlElement
    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }

    @XmlElement
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @XmlElement
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @XmlTransient // Excluye del XML
    public static int getTotalProducts() {
        return totalProducts;
    }

    public static void setTotalProducts(int totalProducts) {
        Product.totalProducts = totalProducts;
    }

    public void expire() {
        this.publicPrice.setValue(this.getPublicPrice().getValue() * EXPIRATION_RATE);
    }

    @XmlElement
    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
    

    @XmlElement
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice=" + wholesalerPrice
                + ", available=" + available + ", stock=" + stock + "]";
    }
}
