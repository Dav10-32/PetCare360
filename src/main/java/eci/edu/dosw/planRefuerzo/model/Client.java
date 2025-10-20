package eci.edu.dosw.planRefuerzo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Invoice> invoices = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
    private List<Appointment> appointments;
}
