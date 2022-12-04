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
        this.setStatus("unapproved");
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
                    //prompt message
                    JOptionPane.showMessageDialog(null, "Login is Successfull!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    //Set up profile
                    Global.CurrentCustomer = custObj;
                    return custObj;
                }
            }
        }
        //prompt message
        JOptionPane.showMessageDialog(null, "Incorrect credentials\n Please Try Again!", "Error Message", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public static void LoadCarTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allCars.size(); i++) {
            //get object
            Car carObj = DataIO.allCars.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{carObj.GetCarPlate(), carObj.GetCarType(), Integer.toString(carObj.GetPrice()), carObj.GetColor()};
            table.addRow(eachRow);
        }
    }

    public static Boolean AddBooking(String carPlate, String startDate, String duration) {
        try {
            //validate data
            if (Customer.validateDate(startDate)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(carPlate);
                list.add(startDate);
                list.add(duration);
                list.add("pending");
                
                Car carObj = null;
                 for (Car car : DataIO.allCars) {
                     if(car.GetCarPlate().equals(carPlate)){
                         carObj = car;
                     }
                 }
                 
                Boolean checkNull = Global.NullValuesExist(list);
                Boolean checkBookingExist = Customer.BookingAlreadyExists(carObj);

                if (checkNull == false && checkBookingExist == false) {
                    String status = "pending";
                    String rating = null;
                    String feedback = null;
                    //create obj
                    Booking newBooking = new Booking(Global.CurrentCustomer, carObj, startDate, Integer.parseInt(duration),"no", status, rating, feedback);
                    //add obj
                    DataIO.allBookings.add(newBooking);
                    //write file
//                    DataIO.WriteFileBooking();
                    JOptionPane.showMessageDialog(null, "New Booking is successfully added!", "New Booking", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "failed to add car!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return null;
    }

    public static Boolean BookingAlreadyExists(Car car) {
        for (Booking obj : DataIO.allBookings) {
            if (obj.getCustomer().getUsername().equals(Global.CurrentCustomer.getUsername()) && obj.getCar().GetCarPlate().equals(car.GetCarPlate())) {
                JOptionPane.showMessageDialog(null, "This booking for car with plate" + car.GetCarPlate() + " already exists! \n Please book a different car", "Already Exists", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    //Validation methods
    public static Boolean validateDate(String stringDate) {
        // Check if date is 'null'
        if (stringDate.trim().equals("")) {
            return false;
        } // Date is not 'null' 
        else {
            //Check date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            // Create Date object, parse the string into date 
            try {
                //Parse string date into date type
                Date parsedDate = sdf.parse(stringDate);
                //System.out.println(parsedDate+" is valid date format");
            } /* Date format is invalid */ catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Wrong date format entered!\nPlease follow the format DD-MM-YYYY");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

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
