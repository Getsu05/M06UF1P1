package dao.xml;

import model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DomWriter {

    public boolean generateDocument(ArrayList<Product> products) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Crear un nuevo documento XML
            Document document = builder.newDocument();
            Element root = document.createElement("inventory");
            document.appendChild(root);

            // Añadir cada producto al XML
            for (Product product : products) {
                Element productElement = document.createElement("product");

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(product.getName()));
                productElement.appendChild(name);

                Element wholesalerPrice = document.createElement("wholesalerPrice");
                wholesalerPrice.appendChild(document.createTextNode(String.valueOf(product.getWholesalerPrice().getValue())));
                productElement.appendChild(wholesalerPrice);

                Element stock = document.createElement("stock");
                stock.appendChild(document.createTextNode(String.valueOf(product.getStock())));
                productElement.appendChild(stock);

                root.appendChild(productElement);
            }

            // Especificar la ruta y nombre del archivo de salida
            String fileName = "files/inventory_" + LocalDate.now().toString() + ".xml";
            File outputFile = new File(fileName);

            // Transformar el DOM en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(outputFile);

            transformer.transform(domSource, streamResult);

            System.out.println("Archivo XML generado correctamente en " + fileName);
            return true; // Indica que el documento se generó correctamente

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error al generar el documento XML: " + e.getMessage());
            e.printStackTrace();
            return false; // Indica que hubo un error al generar el documento
        }
    }
}