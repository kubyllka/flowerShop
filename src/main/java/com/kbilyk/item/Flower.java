package com.kbilyk.item;

import com.kbilyk.constant.FlowerType;

import java.util.Date;
import java.util.Objects;

public class Flower {
    private int id;   // the id of accessory in database
    private FlowerType flowerType;   // the type of flower
    private String colour;         // the colour of flower
    private double length;       // the length of flower
    private double price;       // the price of flower
    private Date date = null;  // date of delivery of the flower to the store (freshness)

    public Flower() {}

    public int getId() {
        return id;
    }

    public FlowerType getFlowerType() {
        return flowerType;
    }

    public String getColour() {
        return colour;
    }

    public double getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", flowerType=" + flowerType +
                ", colour='" + colour + '\'' +
                ", length=" + length +
                ", price=" + price +
                ", date=" + date +
                '}' + '\n';
    }

    public static FlowerBuilder builder(){
        return new FlowerBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id == flower.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public static class FlowerBuilder{

        private final Flower flower;

        FlowerBuilder() {
            this.flower = new Flower();
        }

        public FlowerBuilder setId(int id) {
            flower.id = id;
            return this;
        }

        public FlowerBuilder setFlowerType(FlowerType flowerType) {
            flower.flowerType = flowerType;
            return this;
        }

        public FlowerBuilder setColour(String colour) {
            flower.colour = colour;
            return this;
        }

        public FlowerBuilder setLength(double length) {
            flower.length = length;
            return this;
        }

        public FlowerBuilder setPrice(double price) {
            flower.price = price;
            return this;
        }

        public FlowerBuilder setDate(Date date) {
            flower.date = date;
            return this;
        }

        public Flower built(){
            return flower;
        }

    }
}
