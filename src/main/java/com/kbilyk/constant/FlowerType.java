package com.kbilyk.constant;


public enum FlowerType {

    ROSE(1, "Rose"),

    LAVANDULA(2, "Lavandula"),

    LILY(3, "Lily"),

    LUPINE(4, "Lupine"),

    GYPSOPHILA(5, "Gypsophila"),

    CHAMOMILE(6, "Chamomile"),

    CHRYSANTHEMUM(7, "Chrysanthemum"),

    TULIP(8, "Tulip"),

    DAFFODIL(9, "Daffodil");

    private final int numType;       // the number of that type
    private final String name;      // the name of flower type

    FlowerType(int type, String name) {
        this.numType = type;
        this.name = name;
    }

    /**
     * Get flower type by number of flower type
     * @param numType The number of flower type
     * @return The flower type
     */
    public static FlowerType getFlowerType(int numType){
        for(FlowerType type : FlowerType.values()){
            if(type.numType == numType){
                return type;
            }
        }
        return null;
    }

    /**
     * Get the number of flower type
     * @return The number of flower type
     */
    public int getNumType() {
        return numType;
    }


    @Override
    public String toString() {
        return name;
    }
}
