package com.kbilyk.item;

import com.kbilyk.constant.AccessoryType;

import java.util.Objects;

public class Accessory {
    private int id;         // the id of accessory in database
    private AccessoryType accessoryType;  // the type of accessory
    private String colour;         // the colour of flower
    private double price;        // the price of flower
    private String info;        // the additional info about accessory

    public Accessory() {}

    public static Accessory.AccessoryBuilder builder(){
        return new Accessory.AccessoryBuilder();
    }

    public int getId() {
        return id;
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public String getColour() {
        return colour;
    }

    public double getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", accessoryType=" + accessoryType +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accessory accessory = (Accessory) o;
        return id == accessory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class AccessoryBuilder{
        private final Accessory accessory;

        AccessoryBuilder(){
            accessory = new Accessory();
        }
        public AccessoryBuilder setId(int id) {
            accessory.id = id;
            return this;
        }

        public AccessoryBuilder setAccessoryType(AccessoryType accessoryType) {
            accessory.accessoryType =  accessoryType;
            return this;
        }

        public AccessoryBuilder setColour(String colour) {
            accessory.colour = colour;
            return this;
        }

        public AccessoryBuilder setPrice(double price) {
            accessory.price = price;
            return this;
        }

        public AccessoryBuilder setInfo(String info) {
            accessory.info = info;
            return this;
        }


        public Accessory built(){
        return accessory;
        }
    }
}
