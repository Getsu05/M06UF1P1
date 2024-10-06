package dao;

import model.Product;
import model.Amount;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import main.Shop;  

public class DaoImplFile implements Dao {
    
    private Shop shop; 

    private static final String INVENTORY_FILE = "files/inputInventory.txt";  // Ruta al archivo de inventario

    @Override
    public ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Procesar cada línea y extraer datos del producto
                String[] productDetails = line.split(";");

                // Crear variables auxiliares
                String productName = null;
                double wholesalePrice = 0;
                int stock = 0;

                // Iterar por los detalles del producto
                for (String detail : productDetails) {
                    String[] keyValue = detail.split(":");
                    if (keyValue.length != 2) {
                        // Si no hay exactamente una clave y un valor, saltamos este detalle
                        continue;
                    }

                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("Product")) {
                        productName = value;
                    } else if (key.equals("Wholesaler Price")) {
                        try {
                            wholesalePrice = Double.parseDouble(value);
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir el precio a número: " + value);
                        }
                    } else if (key.equals("Stock")) {
                        try {
                            stock = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir el stock a número: " + value);
                        }
                    }
                }

                // Verificar que se haya leído correctamente el producto antes de agregarlo al inventario
                if (productName != null && wholesalePrice > 0 && stock >= 0) {
                    // Crear el objeto Amount para el precio mayorista
                    Amount wholesalerPriceAmount = new Amount(wholesalePrice);
                    // Instanciar el producto con el constructor adecuado
                    Product product = new Product(productName, wholesalerPriceAmount, true, stock);
                    inventory.add(product);
                } else {
                    System.out.println("Datos incompletos o incorrectos para el producto: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el inventario desde el archivo: " + e.getMessage());
        }

        return inventory;
    }

    @Override
    public boolean writeInventory(ArrayList<Product> inventory) {
        return true;
    }

    @Override
    public void connect() {      
    }

    @Override
    public void disconnect() {       
    }

    @Override
    public model.Employee getEmployee(int employeeId, String password) {
        return null;
    }
}
