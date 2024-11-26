package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;

@XmlRootElement(name = "amount") 
public class Amount {

    private double value;
    private String currency = "€";

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Amount(double value) {
        this.value = value;
    }

    public Amount() {
        
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @XmlElement // Incluir cuando tenga tiempo en el XML
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return df.format(value) + currency;
    }
}