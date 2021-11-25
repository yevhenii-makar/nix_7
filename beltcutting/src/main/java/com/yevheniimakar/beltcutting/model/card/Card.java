package com.yevheniimakar.beltcutting.model.card;

import com.yevheniimakar.beltcutting.model.manufacturer.Manufacturer;
import com.yevheniimakar.beltcutting.model.unit.Unit;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    private Integer count = 0;

    private Long price;

    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id")
    private Card accessory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public Card() {
    }

    public Card(Long id, String name, Integer count, Long price, Integer size, Manufacturer manufacturer, Card accessory, Unit unit) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
        this.manufacturer = manufacturer;
        this.accessory = accessory;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Card getAccessory() {
        return accessory;
    }

    public void setAccessory(Card accessory) {
        this.accessory = accessory;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
