/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Global {

    //current user
    public static Admin CurrentAdmin;
    public static Customer CurrentCustomer;

    public static Boolean NullValuesExist(ArrayList<String> list) {
        for (String i : list) {
            if (i.equals("")) {
                JOptionPane.showMessageDialog(null, "Please don't leave any text field empty");
                return true;
            }
        }
        return false;
    }

    public static Boolean IsInteger(String Userinput, String bannerMsg) {
        try {
            Integer.parseInt(Userinput);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter numbers only for " + bannerMsg + " field");
            return false;
        }
    }

    public static Boolean StringOnly(String Userinput, String bannerMsg) {
        Boolean result = Userinput.matches("[a-zA-Z]+");
        if (result == false) {
            JOptionPane.showMessageDialog(null, "Please enter letters only(no numbers) for " + bannerMsg + " field");
        }
        return result;
    }

    //save for admin logins
    public static void SaveAdminLoginRecord(Admin adminObj) { 
        try {
            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();

            DataIO.allLogins.add(new Login(date.toString(), time.toString(), adminObj.getUsername(), adminObj.getPrivilege()));
            DataIO.WriteFileLogin();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in saveloginrecord");
        }
    }

    //save for customer logins
    public static void SaveCustomerLoginRecord(Customer customerObj) { 
        try {
            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();

            DataIO.allLogins.add(new Login(date.toString(), time.toString(), customerObj.getUsername(), customerObj.getPrivilege()));
            DataIO.WriteFileLogin();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in saveloginrecord");
        }
    }

    //readfile (return full line as a string), using as support method
    public static ArrayList<String> ReadFile(String FileName) {
        ArrayList<String> list = new ArrayList<String>();

        //read file and read each full line
        try {
            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fullLine = myReader.nextLine();
                list.add(fullLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //for debug purpose
            JOptionPane.showMessageDialog(null, "Error in ReadFile");
        }
        return list;
    }

    //add date with number of days
    public static String addDate(String bookingDate, String days) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int parsedDays = Integer.parseInt(days);
        Calendar c = Calendar.getInstance();
        try {
            //Parse the date 
            c.setTime(sdf.parse(bookingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Add number of days
        c.add(Calendar.DAY_OF_MONTH, parsedDays);
        //Date after adding the days to booking date
        String checkoutDate = sdf.format(c.getTime());
        //return checkout date        
        return checkoutDate;
    }
    
    //global validations
    //validate empty date or wrong date format
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
    
    //validate if date entered is before today's date.
    public static Boolean beforeTodayDate(String stringDate){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedTodayDate = today.format(dateTimeFormatter);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            //Parse string date into date type
            Date parsedBookingDate = sdf.parse(stringDate);
            Date parsedTodayDate = sdf.parse(formattedTodayDate);
            if(parsedBookingDate.compareTo(parsedTodayDate) < 0 || parsedBookingDate.compareTo(parsedTodayDate) == 0){
                JOptionPane.showMessageDialog(null, "Please enter a date that is after today's date");   
            return true;
        }
        }
        catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Wrong date format entered!\nPlease follow the format DD-MM-YYYY");
                return true;
        }
        return false;
    }
}
