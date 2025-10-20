package eci.edu.dosw.planRefuerzo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Dog extends Pet {
    @Override public String getType() { return "Dog"; }
}
