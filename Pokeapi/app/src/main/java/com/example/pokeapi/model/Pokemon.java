package com.example.pokeapi.model;



import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private double height; // Cambiado a double

    @SerializedName("weight")
    private double weight; // Cambiado a double


    public Pokemon(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }


    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

