/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

/**
 *
 * @author Lenovo
 */
public class Car {

    private String CarPlate;
    public String CarType;
    public int PricePerDay;
    public String CarColor;

    public Car(String plate, String type, int price, String color) {
        this.CarPlate = plate;
        this.CarType = type;
        this.PricePerDay = price;
        this.CarColor = color;
    }

    public String GetCarPlate() {
        return this.CarPlate;
    }

    public String GetCarType() {
        return this.CarType;
    }

    public int GetPrice() {
        return this.PricePerDay;
    }

    public String GetColor() {
        return this.CarColor;
    }

    public void setCarPlate(String CarPlate) {
        this.CarPlate = CarPlate;
    }

    public void setCarType(String CarType) {
        this.CarType = CarType;
    }

    public void setPricePerDay(int PricePerDay) {
        this.PricePerDay = PricePerDay;
    }

    public void setCarColor(String CarColor) {
        this.CarColor = CarColor;
    }

}
