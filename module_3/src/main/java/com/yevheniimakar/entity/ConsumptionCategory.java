package com.yevheniimakar.entity;
import javax.persistence.*;

@Entity
@Table(name="consumptioncategories")

public class ConsumptionCategory extends Category{

    @Column(name ="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
