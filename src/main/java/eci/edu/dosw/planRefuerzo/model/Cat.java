package eci.edu.dosw.planRefuerzo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cat extends Pet {
    @Override public String getType() { return "Cat"; }
}

