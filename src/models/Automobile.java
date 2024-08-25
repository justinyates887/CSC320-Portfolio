package models;

import interfaces.IAutomobile;

public class Automobile implements IAutomobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    // Parameterized constructor
    public Automobile(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
    }

    @Override
    public String[] listVehicle() {
        return new String[] {
            "Make: " + make,
            "Model: " + model,
            "Color: " + color,
            "Year: " + year,
            "Mileage: " + mileage
        };
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMileage() {
        return mileage;
    }
}