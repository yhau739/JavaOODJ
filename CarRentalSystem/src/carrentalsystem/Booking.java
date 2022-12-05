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
    private int Duration;
    private String BookStatus;
    private String EndDate;
    private int Payment;
    private String PaymentStatus;
    private String Rating;
    private String Feedback;
    private String Collected;
    
    //constructor
    public Booking(Customer customer, Car car, String StartDate, int Duration, String BookStatus, String PaymentStatus, String Rating, String Feedback, String Collected){
        this.customer = customer;
        this.car = car;
        this.StartDate = StartDate;
        this.Duration = Duration;
        this.BookStatus = BookStatus;
        //calculate end date
        this.EndDate = Global.addDate(StartDate, Integer.toString(Duration));
        //calculate payment
        this.Payment = car.GetPrice() * Duration;
        this.PaymentStatus = PaymentStatus;
        this.Rating = Rating;
        this.Feedback = Feedback;
        this.Collected = Collected;
    }

    public String getCollected() {
        return Collected;
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
    
    public int getPayment() {
        return Payment;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public String getRating() {
        return Rating;
    }

    public void setCollected(String Collected) {
        this.Collected = Collected;
    }

    public String getFeedback() {
        return Feedback;
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

    public void setPayment(int Payment) {
        this.Payment = Payment;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public void setFeedback(String Feedback) {
        this.Feedback = Feedback;
    }
}
