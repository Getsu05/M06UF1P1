package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

import model.Amount;
import model.Product;
import dao.DaoImplFile;
import main.Shop;

public class ShopView extends JFrame {
    private DaoImplFile dao;
    private Shop shop;  // Instancia de Shop para acceder a la caja

    // Constructor
    public ShopView() {
        this.dao = new DaoImplFile();
        this.shop = new Shop();  // Inicializa la tienda

        // Configuración de la ventana
        setTitle("Gestión de la Tienda");
        setSize(400, 400);  // Ajustar tamaño para 9 botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));  // Disposición para 9 botones

        // Crear botones
        JButton exportInventoryButton = new JButton("0. Exportar inventario");
        JButton countCashButton = new JButton("1. Contar caja");
        JButton addProductButton = new JButton("2. Añadir producto");
        JButton addStockButton = new JButton("3. Añadir stock"); 
        JButton removeProductButton = new JButton("9. Eliminar producto");
        JButton exitButton = new JButton("10. Salir");

        // Añadir listeners a los botones
        exportInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportInventory();
            }
        });

        countCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countCashRegister();  
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí irá la nueva funcionalidad que se desee implementar para el botón 3
                JOptionPane.showMessageDialog(null, "Nueva funcionalidad en desarrollo.");
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();  // Llamar al método para eliminar un producto
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Añadir los botones al frame
        add(exportInventoryButton);
        add(countCashButton);
        add(addProductButton);
        add(addStockButton);
        add(removeProductButton);  // Mover a la posición 9
        add(exitButton);
    }

    // Método para mostrar el dinero en la caja, accediendo a la variable cash en Shop
    public void countCashRegister() {
        Amount cash = shop.getCash();  // Obtener el valor de cash desde Shop
        JOptionPane.showMessageDialog(this, "Dinero en caja: €" + cash);
    }

    // Método para exportar el inventario
    public void exportInventory() {
        boolean isExported = shop.writeInventory();

        if (isExported) {
        	JOptionPane.showMessageDialog(this, "El inventario ha sido exportado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un problema al exportar el inventario.");
        }
    }

    // Método para añadir un producto (placeholder)
    public void addProduct() {
        JOptionPane.showMessageDialog(this, "Función para añadir producto en desarrollo.");
    }

    // Método para eliminar un producto (placeholder)
    public void removeProduct() {
        JOptionPane.showMessageDialog(this, "Función para eliminar producto en desarrollo.");
    }

    // Método principal para mostrar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShopView view = new ShopView();
                view.setVisible(true);
            }
        });
    }
}
