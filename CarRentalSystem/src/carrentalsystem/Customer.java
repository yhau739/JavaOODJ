package carrentalsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leere
 */
public class Customer extends User {

    private String Gender;
    private String Age;
    private String Phone;
    private String Email;
    private String Address;
    private String Card;
    private String Status;

    @Override
    //Set current user privilege to customer    
    public void setPrivilege() {
        this.Privilege = "Customer";
    }

    public String getGender() {
        return Gender;
    }

    public String getAge() {
        return Age;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getStatus() {
        return Status;
    }

    public String getCard() {
        return Card;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCard(String Card) {
        this.Card = Card;
    }

    //constructor
    public Customer(String Username, String Password, String Gender, String Age, String Phone, String Email, String Address, String Card, String Status) {
        this.setUsername(Username);
        this.setPassword(Password);
        this.setGender(Gender);
        this.setAge(Age);
        this.setPhone(Phone);
        this.setEmail(Email);
        this.setAddress(Address);
        this.setCard(Card);
        this.setStatus(Status);
        this.setPrivilege();
    }

    public static Boolean Register(String NewUsername, String NewPassword, String NewGender, String NewAge, String NewPhone, String NewEmail, String NewAddress, String NewCard, String NewStatus) {
        try {
            //add data
            DataIO.allCustomers.add(new Customer(NewUsername, NewPassword, NewGender, NewAge, NewPhone, NewEmail, NewAddress, NewCard, NewStatus));
            //after insert, write
            DataIO.WriteFileCustomer();
            JOptionPane.showMessageDialog(null, "You have successfully registed as " + NewUsername, "New Customer", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in AddNewAdmin");
            return false;
        }
    }

    public static Boolean EditProfile(String username, String password, String gender, String age, String phone, String email, String address, String card, String status) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(username);
        list.add(password);
        list.add(gender);
        list.add(age);
        list.add(phone);
        list.add(email);
        list.add(address);
        list.add(card);
        list.add(status);

        if (Global.NullValuesExist(list)) {
            return false;
        }

        for (Customer cust : DataIO.allCustomers) {
            //admin found
            if (cust.getUsername().equals(username)) {
                //Edit values
                cust.setPassword(password);
                cust.setGender(gender);
                cust.setAge(age);
                cust.setPhone(phone);
                cust.setEmail(email);
                cust.setAddress(address);
                cust.setCard(card);
                cust.setStatus(status);

                //Write to File
                DataIO.WriteFileCustomer();
                //Admin.SaveEventLogs(Global.currentadmin);
                JOptionPane.showMessageDialog(null, "Profile edited is successfully!");
                return true;
            }
        }
        return false;
    }

    //check login status for customer
    public static Customer CheckLoginCustomer(String UsernameInput, String PasswordInput) {
        //loop in the size of the array list (number of customers)
        for (Customer custObj : DataIO.allCustomers) {
            if (custObj.getUsername().equals(UsernameInput)) {
                if (custObj.getPassword().equals(PasswordInput)) {
                    if(custObj.getStatus().equals("approve")){
                        //prompt message
                        JOptionPane.showMessageDialog(null, "Login is Successfull!", "Login", JOptionPane.INFORMATION_MESSAGE);
                        //Set up profile
                        Global.CurrentCustomer = custObj;
                        return custObj;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Your account is not approved, please try again later", "Account not approved", JOptionPane.INFORMATION_MESSAGE);
                        return null;
                    }
                }
            }
        }
        //prompt message
        JOptionPane.showMessageDialog(null, "Incorrect credentials\n Please Try Again!", "Error Message", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public static void LoadCarTable(DefaultTableModel table) {
        //get list of car plates that has been booked
        ArrayList<String> list = new ArrayList<String>();
        for (Booking bookings : DataIO.allBookings) {
            String bookingCarPlate = bookings.getCar().GetCarPlate();
            String bookingStatus = bookings.getBookStatus();
            //if car is booked add car plate to list
            if(!bookingStatus.equals("notavailable") && !bookingStatus.equals("returned")){
               list.add(bookingCarPlate);
            }
        }
        
        for (int i = 0; i < DataIO.allCars.size(); i++) {
            //get object
            Car carObj = DataIO.allCars.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{carObj.GetCarPlate(), carObj.GetCarType(), Integer.toString(carObj.GetPrice()), carObj.GetColor()};
            if(!list.contains(carObj.GetCarPlate())){
                table.addRow(eachRow);
            }else{
                System.out.println(list);
                System.out.println(carObj.GetCarPlate());
            }
        }
    }
    
    public static void LoadApprovedBookingTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookingObj = DataIO.allBookings.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookingObj.getCar().GetCarPlate(),bookingObj.getCar().GetCarType(),bookingObj.getCar().GetColor(), bookingObj.getStartDate(), Integer.toString(bookingObj.getDuration()), bookingObj.getEndDate(), Integer.toString(bookingObj.getPayment())};
            String bookingStatus = bookingObj.getBookStatus();
            String paymentStatus = bookingObj.getPaymentStatus();
            String customer = bookingObj.getCustomer().getUsername();
            if (customer.equals(Global.CurrentCustomer.getUsername()) && bookingStatus.equals("available") && paymentStatus.equals("no")){
                table.addRow(eachRow);
            }
        }
    }
    
    public static void LoadReturnTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookingObj = DataIO.allBookings.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookingObj.getCar().GetCarPlate(), bookingObj.getStartDate(), Integer.toString(bookingObj.getDuration()), bookingObj.getEndDate()};
            String bookingStatus = bookingObj.getBookStatus();
            String paymentStatus = bookingObj.getPaymentStatus();
            String customer = bookingObj.getCustomer().getUsername();
            if (customer.equals(Global.CurrentCustomer.getUsername()) && bookingStatus.equals("booked") && paymentStatus.equals("yes")){
                table.addRow(eachRow);
            }
        }
    }
    
    public static void LoadReceiptTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookingObj = DataIO.allBookings.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookingObj.getCustomer().getUsername(), bookingObj.getCar().GetCarPlate(),bookingObj.getCar().GetCarType(), Integer.toString(bookingObj.getCar().GetPrice()), bookingObj.getCar().GetColor(), bookingObj.getStartDate(), bookingObj.getEndDate(), Integer.toString(bookingObj.getDuration()), Integer.toString(bookingObj.getPayment())};
            String bookingStatus = bookingObj.getBookStatus();
            String paymentStatus = bookingObj.getPaymentStatus();
            String customer = bookingObj.getCustomer().getUsername();
            if (customer.equals(Global.CurrentCustomer.getUsername()) && bookingStatus.equals("booked") && paymentStatus.equals("yes")){
                table.addRow(eachRow);
            }
        }
    }

    public static Boolean AddBooking(String carPlate, String startDate, String duration) {
        try {
            //validate data
            ArrayList<String> list = new ArrayList<String>();
            list.add(carPlate);
            list.add(startDate);
            list.add(duration);
            list.add("pending");
            
            Boolean checkNull = Global.NullValuesExist(list);
            
            if (Global.validateDate(startDate)) {
                if (Global.beforeTodayDate(startDate) == false && Global.IsInteger(duration, "duration")){
                    Car carObj = null;
                    int price = 0;
                    for (Car car : DataIO.allCars) {
                        if(car.GetCarPlate().equals(carPlate)){
                            carObj = car;
                            price = car.GetPrice();
                        }
                    }
                     
                    Boolean checkBookingExist = Customer.BookingAlreadyExists(carObj);
                    int total = price*Integer.parseInt(duration);

                    if (checkNull == false && checkBookingExist == false) {
                        int result = JOptionPane.showConfirmDialog(null,"Your total will be RM" + total + "\nPayment will be done after staff approves the booking.\nConfirm Booking?", "Total Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_OPTION){
                            String BookingStatus = "pending";
                            String PaymentStatus = "no";
                            String rating = "none";
                            String feedback = "none";
                            //create obj
                            Booking newBooking = new Booking(Global.CurrentCustomer, carObj, startDate, Integer.parseInt(duration), BookingStatus, PaymentStatus, rating, feedback);
                            //add obj
                            DataIO.allBookings.add(newBooking);
                            //write file
                            DataIO.WriteFileBooking();
                            JOptionPane.showMessageDialog(null, "Successfully added new Booking for car " + carPlate, "New Booking", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Booking has been cancelled!", "Booking cancellation", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to book car.", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.print(e);
            return false;
        }
        return false;
    }
    
    public static Boolean DeleteBooking(String carplate) {
        if (Customer.CheckBookingSelected(carplate) == false){
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete booking for car " + carplate + "?\n You can not undo this action.", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                for (Booking bookingObj : DataIO.allBookings) {
                    if (bookingObj.getCar().GetCarPlate().equals(carplate)) {
                        DataIO.allBookings.remove(bookingObj);
                        //Write to File
                        DataIO.WriteFileBooking();
                        JOptionPane.showMessageDialog(null, "Booking is deleted successfully!\nThere are many cars to rent,we hope you will use our service again!");
                        return true;
                    }
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    public static Boolean ProcessPayment(String CarPlate, String Total){
        if (Customer.CheckBookingSelected(CarPlate) == false){
            int result = JOptionPane.showConfirmDialog(null,"Your total will be RM" + Total + " for car " + CarPlate + ".\nConfirm payment?", "Payment Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                for(Booking booking : DataIO.allBookings){
                    //booking found
                    if (booking.getCar().GetCarPlate().equals(CarPlate)){
                        //Edit values
                        booking.setBookStatus("booked");
                        booking.setPaymentStatus("yes");

                        //Write to File
                        DataIO.WriteFileBooking();

                        JOptionPane.showMessageDialog(null, "Successfully paid RM" + Total + " for car " + CarPlate + "!\nPlease retrive your car at the compound by showing the receipt generated to you", "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }
            }    
            else{
                JOptionPane.showMessageDialog(null, "Payment has been cancelled", "Payment cancellation", JOptionPane.INFORMATION_MESSAGE);
                return false;   
            }
        }
        return false;
    }
    
    public static Boolean ReturnCar(String CarPlate){
        if (Customer.CheckBookingSelected(CarPlate) == false){
            int result = JOptionPane.showConfirmDialog(null,"Confirm to return car" + CarPlate + "?\nYou will not be able to use the car anymore.", "Return Car Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                for(Booking booking : DataIO.allBookings){
                    //booking found
                    if (booking.getCar().GetCarPlate().equals(CarPlate)){
                        //Edit values
                        booking.setBookStatus("returned");
                        booking.setPaymentStatus("yes");

                        //Write to File
                        DataIO.WriteFileBooking();

                        JOptionPane.showMessageDialog(null, "Successfully returned car " + CarPlate + "!\nThank you for using our service.\nWe hope to see you again!","Return Successful", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }
            }    
            else{
                JOptionPane.showMessageDialog(null, "Car return has been cancelled", "Car return cancellation", JOptionPane.INFORMATION_MESSAGE);
                return false;   
            }
        }
        return false;
    }

    public static Boolean CheckBookingSelected(String CarPlate){
        // Car plate is 'null' 
        if (CarPlate.equals("")) {
            JOptionPane.showMessageDialog(null, "Please select a booking information","No booking information selected", JOptionPane.ERROR_MESSAGE);
            return true;
        } // Car plate is not 'null' 
        return false;
    }
    
    public static Boolean BookingAlreadyExists(Car car) {
        for (Booking obj : DataIO.allBookings) {
            if (obj.getCustomer().getUsername().equals(Global.CurrentCustomer.getUsername()) && obj.getCar().GetCarPlate().equals(car.GetCarPlate()) && (!obj.getBookStatus().equals("notavailable") || !obj.getBookStatus().equals("returned"))) {
                JOptionPane.showMessageDialog(null, "This booking for car with plate" + car.GetCarPlate() + " already exists! \n Please book a different car", "Already Exists", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    //Validation methods
    public static Boolean CustomerAlreadyExists(String username, String bannerMsg) {
        for (Customer obj : DataIO.allCustomers) {
            if (obj.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "This " + bannerMsg + " already exists! \n Please Enter a different " + bannerMsg, "Already Exists", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public static Boolean checkMatchPsw(String psw, String cfmPsw) {
        if (!psw.equals(cfmPsw)) {
            JOptionPane.showMessageDialog(null, "The password entered does not match!", "Password does not match", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static Boolean validateAge(String age) {
        try {
            int numbAge = Integer.parseInt(age);
            if (numbAge <= 0 || numbAge > 100) {
                JOptionPane.showMessageDialog(null, "Your age is incorrect", "Enter a correct age", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "You did not enter a number for age", "Enter a number for age", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static Boolean validatePhone(String phone) {
        // Contact Validation
        String regexContact = "^(\\+?6?01)[0-46-9]-*[0-9]{7,8}$";
        Pattern phonePattern = Pattern.compile(regexContact);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        boolean phoneValid = phoneMatcher.find();
        //If invalid email format clear the text box        
        if (!phoneValid) {
            JOptionPane.showMessageDialog(null, "Wrong contact format entered.\nRecommeded contact format: '0123456789'", "Wrong contact format", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static Boolean validateEmail(String email) {
        // Regular Expression   
        String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        //Compile regular expression to get the pattern  
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        boolean emailValid = matcher.find();
        //If invalid email format clear the text box        
        if (!emailValid) {
            JOptionPane.showMessageDialog(null, "Email format invalid.\nRecommeded Email format: example@domain.com", "Email format wrong", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static Boolean validateCard(String card) {
        try {
            long numbCard = Long.parseLong(card);
            if (numbCard < 13 && numbCard > 16) {
                JOptionPane.showMessageDialog(null, "Credit card number is 13-16 digits", "Enter correct number length", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "You did not enter number for card", "Enter a number for card", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
