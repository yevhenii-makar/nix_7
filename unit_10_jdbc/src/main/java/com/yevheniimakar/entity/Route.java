package com.yevheniimakar.entity;

public class Route {

    private int id;
    private int fromId;
    private int toId;
    private int coast;

    public Route(int id, int fromId, int toId, int coast) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.coast = coast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

}
