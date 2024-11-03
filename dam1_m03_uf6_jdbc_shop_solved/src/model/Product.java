package model;

public class Product {
    private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts;
    
    // Atributos adicionales para badge y color
    private String badge;
    private String color;

    public final static double EXPIRATION_RATE = 0.60;

    // Constructor sin parámetros
    public Product() {
        this.id = ++totalProducts;  // Incrementamos el id único para cada producto
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

    // Getters y Setters para los atributos principales
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

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

    public void expire() {
        this.publicPrice.setValue(this.getPublicPrice().getValue() * EXPIRATION_RATE);
    }

    // Getters y Setters para los nuevos atributos badge y color
    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Setter para 'storage' como alias de 'stock' para ajustarse a `SaxReader.java`
    public void setStorage(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice=" + wholesalerPrice
                + ", available=" + available + ", stock=" + stock + "]";
    }
}
