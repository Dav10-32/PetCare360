package eci.edu.dosw.planRefuerzo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Invoice {
    private Long id;
    private Long clientId;
    private Long petId;
    private List<ServiceItem> services = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private double taxes;
    private double total;

    public double calculateTotal() {
        double subtotal = 0;
        for (ServiceItem s : services) subtotal += s.getPrice();
        for (Product p : products) subtotal += p.getPrice();
        total = subtotal + taxes;
        return total;
    }
}

