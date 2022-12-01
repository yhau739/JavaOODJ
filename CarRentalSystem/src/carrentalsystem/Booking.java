/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leere
 */
public class Booking {
    private Customer customer;
    private Car car;
    private String StartDate;
    private String EndDate;
    private int Duration;
    private int Payment;
    private String BookStatus;
    
    
    //constructor
    public Booking(Customer customer, Car car, String StartDate, int Duration, String BookStatus){
        this.customer = customer;
        this.car = car;
        this.StartDate = StartDate;
        this.Duration = Duration;
        this.BookStatus = BookStatus;
        //calculate end date
        this.EndDate = Global.addDate(StartDate, Integer.toString(Duration));
        //calculate payment
        this.Payment = car.GetPrice() * Duration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public int getDuration() {
        return Duration;
    }
    
    public String getBookStatus() {
        return BookStatus;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public void setBookStatus(String BookStatus) {
        this.BookStatus = BookStatus;
    }

    public int getPayment() {
        return Payment;
    }

    public void setPayment(int Payment) {
        this.Payment = Payment;
    }
    
    
    
    //Load Table Section in Feedback Section for customer
    public static void LoadBookingTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookingObj = DataIO.allBookings.get(i);
            //get values and make it into an array
//            String[] eachRow = new String[]{bookingObj.getUsername(), bookingObj.getPassword(), bookingObj.getCreatedOnDate(), bookingObj.getCreatedOnTime()};

//            table.addRow(eachRow);
        }
    }
}
