package eci.edu.dosw.planRefuerzo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Pet {
    private Long id;
    private String name;
    private int age;
    private String record;
    private Long ownerId;


    public abstract String getType();
}
