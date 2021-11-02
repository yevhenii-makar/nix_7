package com.yevheniimakar.beltcutting.model;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturers_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessories_id")
    private Card accessory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "units_id")
    private Unit unit;


    public long getId() {
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


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
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

}
