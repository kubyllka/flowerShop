package com.kbilyk.constant;

public enum AccessoryType {

    GELBALL(1, "Gel ball"),

    POSTCARD(2, "Postcard"),

    HEART(3, "Heart"),

    PAPER (4, "Gift paper" ),

    RIBBON (5, "Ribbon"),

    BEAR (6, "Bear");

    private final int numType;      // the number of that type

    private final String name;      // the name of accessory type

    AccessoryType(int numType, String name) {
        this.numType = numType;
        this.name = name;
    }

    /**
     * Get accessory type by number of accessory type
     * @param numType The number of accessory type
     * @return The accessory type
     */
    public static AccessoryType getAccessoryType(int numType){
        for(AccessoryType type : AccessoryType.values()){
            if(type.numType == numType){
                return type;
            }
        }
        return null;
    }

    /**
     * Get the number of accessory type
     * @return The number of accessory type
     */
    public int getNumType() {
        return numType;
    }

    @Override
    public String toString() {
        return name;
    }
}
