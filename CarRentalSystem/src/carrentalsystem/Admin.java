/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import static carrentalsystem.Global.ReadFile;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
//inheritance
public class Admin extends User {

    private String CreatedOnDate;
    private String CreatedOnTime;
    private String Event;
    public static String CurrentCarPlate;

    @Override
    public void setPrivilege() {
        this.Privilege = "admin";
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String Event) {
        this.Event = Event;
    }

    public String getCreatedOnDate() {
        return CreatedOnDate;
    }

    public void setCreatedOnDate(String CreatedOnDate) {
        this.CreatedOnDate = CreatedOnDate;
    }

    public String getCreatedOnTime() {
        return CreatedOnTime;
    }

    public void setCreatedOnTime(String CreatedOnTime) {
        this.CreatedOnTime = CreatedOnTime;
    }

    //constructor
    public Admin(String Username, String Password, String CreatedOnDate, String CreatedOnTime) {
        this.setUsername(Username);
        this.setPassword(Password);
        this.setEvent("");
        this.CreatedOnDate = CreatedOnDate;
        this.CreatedOnTime = CreatedOnTime;
        this.setPrivilege();
    }

    //Backend Section
    public static void SaveEventLogs(Admin adminObj) {
        try {
            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            DataIO.allEvents.add(new Event(adminObj, adminObj.Event, date.format(formatters), time.toString()));
            DataIO.WriteFileEvent();
        } catch (Exception e) {
            System.out.println("Failed to save event logs");
        }
    }

    //Functionality Section
    public static Boolean EditBooking(String username, String carPlate, String startDate, String Duration) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(carPlate);
        list.add(startDate);
        list.add(Duration);

        //validate null values
        if (Global.NullValuesExist(list)) {
            return false;
        }

        for (Booking i : DataIO.allBookings) {
            //find booking
            if (i.getCustomer().getUsername().equals(username)) {
                if (i.getCar().GetCarPlate().equals(carPlate)) {
                    //Edit values
                    //if car is changed
                    if (!Admin.CurrentCarPlate.equals(carPlate)) {
                        //check if the new car is taken
                        for (Booking b : DataIO.allBookings) {
                            if (b.getCar().GetCarPlate().equals(carPlate)) {
                                //car is taken, can not change to this car
                                JOptionPane.showMessageDialog(null, "This car is unavailable, please choose another one!");
                                return false;
                            }
                        }
                        //passed validation, switch car for booking
                        for (Car c : DataIO.allCars) {
                            if (c.GetCarPlate().equals(carPlate)) {
                                i.setCar(c);
                            }
                        }
                    }
                    //validate if date is already passed
                    //insert code here ...
                    i.setStartDate(startDate);
                    Boolean isInt = Global.IsInteger(Duration, "Duration");
                    if (!isInt) {
                        return false;
                    }
                    i.setDuration(Integer.parseInt(Duration));
                    //Write to File
                    DataIO.writeFileBooking();
                    //save events
                    Global.CurrentAdmin.setEvent("EditBooking");
                    //Admin.SaveEventLogs(Global.currentadmin);
                    JOptionPane.showMessageDialog(null, "Modification is successful!");
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Booking> SearchBooking(String customerUsername, String attribute) {
        ArrayList<Booking> MatchList = new ArrayList<Booking>();
        for (Booking i : DataIO.allBookings) {
            switch (attribute) {
                case "Customer":
                    if (i.getCustomer().getUsername().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Car Plate":
                    if (i.getCar().GetCarPlate().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Start Date":
                    if (i.getStartDate().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "End Date":
                    if (i.getEndDate().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Duration":
                    if (Integer.toString(i.getDuration()).matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Status":
                    if (i.getBookStatus().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Payment Status":
                    if (i.getPaymentStatus().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
            }
        }
        return MatchList;
    }

    public static Boolean DeleteBooking(String username, String carplate) {
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getCustomer().getUsername().equals(username)) {
                if (bookObj.getCar().GetCarPlate().equals(carplate)) {
                    DataIO.allBookings.remove(bookObj);
                    Global.CurrentAdmin.setEvent("DeleteBooking");
                    //Admin.SaveEventLogs(Global.currentadmin);
                    JOptionPane.showMessageDialog(null, "Booking is deleted successfully!");
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean ApproveBooking(String username, String carplate) {
        //get Booking obj
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getCustomer().getUsername().equals(username)) {
                if (bookObj.getCar().GetCarPlate().equals(carplate)) {
                    if (bookObj.getBookStatus().equals("booked")) {
                        JOptionPane.showMessageDialog(null, "This booking is already approved!");
                        return false;
                    }
                    //approve
                    bookObj.setBookStatus("booked");
                    Global.CurrentAdmin.setEvent("ApproveBooking");
                    //Admin.SaveEventLogs(Global.currentadmin);
                    JOptionPane.showMessageDialog(null, "Booking is approved!");
                    return true;
                }
            }
        }
    }

    public static Boolean AddCar(String CarPlate, String CarType, String Price, String color) {
        try {
            ArrayList<String> list = new ArrayList<String>();
            list.add(CarPlate);
            list.add(CarType);
            list.add(Price);
            list.add(color);
            Boolean validation = Global.NullValuesExist(list);
            Boolean validation2 = Admin.CarAlreadyExists(CarPlate, "CarPlate");

            if (validation == false && validation2 == false) {
                //create obj
                Car newCar = new Car(CarPlate, CarType, Integer.parseInt(Price), color);
                //add obj
                DataIO.allCars.add(newCar);
                //write file
                DataIO.WriteFileCar();
                JOptionPane.showMessageDialog(null, "New Car is successfully added!", "New Car", JOptionPane.INFORMATION_MESSAGE);
                Global.CurrentAdmin.setEvent("AddNewCar");
                //Admin.SaveEventLogs(Global.currentadmin);
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "failed to add car!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return false;
    }

    public static ArrayList<Car> SearchCar(String CarPlate, String attribute) {
        ArrayList<Car> MatchList = new ArrayList<Car>();
        for (Car i : DataIO.allCars) {
            switch (attribute) {
                case "Car Plate":
                    if (i.GetCarPlate().matches("(.*)" + CarPlate + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Type":
                    if (i.GetCarType().matches("(.*)" + CarPlate + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Price":
                    if (Integer.toString(i.GetPrice()).matches("(.*)" + CarPlate + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Color":
                    if (i.GetColor().matches("(.*)" + CarPlate + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
            }
        }
        return MatchList;
    }

    public static Boolean DeleteCar(String carplate) {
        for (Car carObj : DataIO.allCars) {
            if (carObj.GetCarPlate().equals(carplate)) {
                DataIO.allCars.remove(carObj);
                Global.CurrentAdmin.setEvent("DeleteCar");
                //Admin.SaveEventLogs(Global.currentadmin);
                JOptionPane.showMessageDialog(null, "Car is deleted successfully!");
                return true;
            }
        }
        return false;
    }

    public static Boolean EditCar(String CarPlate, String CarType, String Price, String CarColor) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(CarPlate);
        list.add(CarType);
        list.add(Price);
        list.add(CarColor);

        if (Global.NullValuesExist(list)) {
            return false;
        }

        for (Car i : DataIO.allCars) {
            //Car found
            if (i.GetCarPlate().equals(CarPlate)) {
                //validation Integer input
                Boolean isInt = Global.IsInteger(Price, "Price");
                Boolean isStr1 = Global.StringOnly(CarType, "Car Type");
                Boolean isStr2 = Global.StringOnly(CarColor, "Car Color");

                //pass validation
                if (isInt && isStr1 && isStr2) {
                    //Edit values
                    i.setCarPlate(CarPlate);
                    i.setCarType(CarType);
                    i.setPricePerDay(Integer.parseInt(Price));
                    i.setCarColor(CarColor);

                    //Write to File
                    DataIO.WriteFileCar();
                    Global.CurrentAdmin.setEvent("EditCar");
                    //Admin.SaveEventLogs(Global.currentadmin);
                    JOptionPane.showMessageDialog(null, "Modification is successful!");
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean AddNewAdmin(String NewUsername, String NewPassword) {
        try {
            //create list
            ArrayList<String> list = new ArrayList<String>();
            list.add(NewUsername);
            list.add(NewPassword);
            //password validation problem
            if (String.valueOf(NewPassword).length() < 5) {
                JOptionPane.showMessageDialog(null, "Password length must be at least 5 characters long!", "Invalid Password", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            //validation
            Boolean validation1 = Admin.AdminAlreadyExists(NewUsername, "Admin Username");
            Boolean validation2 = Global.NullValuesExist(list);

            if (validation1 == true || validation2 == true) {
                return false;
            }

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalTime time = LocalTime.now();
            //add data
            DataIO.allAdmins.add(new Admin(NewUsername, NewPassword, date.format(formatters), time.toString()));
            //after insert, write
            DataIO.WriteFileAdmin();
            JOptionPane.showMessageDialog(null, "New Admin is successfully added!", "New Admin", JOptionPane.INFORMATION_MESSAGE);
            Global.CurrentAdmin.setEvent("AddNewAdmin");
            //Admin.SaveEventLogs(Global.currentadmin);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in AddNewAdmin");
            return false;
        }
    }

    public static ArrayList<Admin> SearchAdmin(String Username, String attribute) {
        ArrayList<Admin> MatchList = new ArrayList<Admin>();
        for (Admin i : DataIO.allAdmins) {
            switch (attribute) {
                case "Username":
                    if (i.getUsername().matches("(.*)" + Username + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "CreatedDate":
                    if (i.getCreatedOnDate().matches("(.*)" + Username + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "CreatedTime":
                    if (i.getCreatedOnTime().matches("(.*)" + Username + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
            }
        }
        return MatchList;
    }

    public static Boolean DeleteAdmin(String username) {
        for (Admin adminObj : DataIO.allAdmins) {
            if (adminObj.getUsername().equals(username)) {
                DataIO.allAdmins.remove(adminObj);
                Global.CurrentAdmin.setEvent("DeleteAdmin");
                //Admin.SaveEventLogs(Global.currentadmin);
                JOptionPane.showMessageDialog(null, "Admin is deleted successfully!");
                return true;
            }
        }
        return false;
    }

    public static Boolean EditAdmin(String username, String password) {
        //password validation
        if (String.valueOf(password).length() < 5) {
            JOptionPane.showMessageDialog(null, "Password length must be at least 5 characters long!", "Invalid Password", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        ArrayList<String> list = new ArrayList<String>();
        list.add(username);
        list.add(password);

        if (Global.NullValuesExist(list)) {
            return false;
        }

        for (Admin i : DataIO.allAdmins) {
            //admin found
            if (i.getUsername().equals(username)) {
                //Edit values
                i.setPassword(password);
                //Write to File
                DataIO.WriteFileAdmin();
                Global.CurrentAdmin.setEvent("EditAdmin");
                //Admin.SaveEventLogs(Global.currentadmin);
                JOptionPane.showMessageDialog(null, "Modification is successful!");
                return true;
            }
        }
        return false;
    }

    //check login status for admin
    public static Admin CheckLoginAdmin(String UsernameInput, String PasswordInput) {
        for (Admin adminObj : DataIO.allAdmins) {
            if (adminObj.getUsername().equals(UsernameInput)) {
                if (adminObj.getPassword().equals(PasswordInput)) {
                    //prompt message
                    JOptionPane.showMessageDialog(null, "Login is Successfull!", "Login", JOptionPane.INFORMATION_MESSAGE);

                    //Set up profile
                    Global.CurrentAdmin = adminObj;
                    //Global.SaveAdminLoginRecord(result); 
                    return adminObj;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Incorrect credentials\n Please Try Again!", "Error Message", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public static Boolean AdminAlreadyExists(String username, String bannerMsg) {
        for (Admin obj : DataIO.allAdmins) {
            if (obj.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "This " + bannerMsg + " already exists! \n Please Enter a different " + bannerMsg, "Already Exists", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public static Boolean CarAlreadyExists(String userInput, String bannerMsg) {
        for (Car obj : DataIO.allCars) {
            if (obj.GetCarPlate().equals(userInput)) {
                JOptionPane.showMessageDialog(null, "This " + bannerMsg + " already exists! \n Please Enter a different " + bannerMsg, "Already Exists", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    //Load Table Section
    public static void LoadAdminTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allAdmins.size(); i++) {
            //get object
            Admin adminObj = DataIO.allAdmins.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{adminObj.getUsername(), adminObj.getPassword(), adminObj.getCreatedOnDate(), adminObj.getCreatedOnTime()};

            table.addRow(eachRow);
        }
    }

    public static void LoadSearchedAdminTable(DefaultTableModel table, ArrayList<Admin> adminList) {
        for (int i = 0; i < adminList.size(); i++) {
            //get object
            Admin adminObj = adminList.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{adminObj.getUsername(), adminObj.getPassword(), adminObj.getCreatedOnDate(), adminObj.getCreatedOnTime()};
            table.addRow(eachRow);
        }
    }

    public static void LoadEventTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allEvents.size(); i++) {
            //get object
            Event eventObj = DataIO.allEvents.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{eventObj.getAdmin().getUsername(), eventObj.getEventType(), eventObj.getDate(), eventObj.getTime()};

            table.addRow(eachRow);
        }
    }

    public static void LoadLoginTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allLogins.size(); i++) {
            //get object
            Login loginObj = DataIO.allLogins.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{loginObj.getLoginDate(), loginObj.getLoginTime(), loginObj.getLoginUsername(), loginObj.getPrivillege()};

            table.addRow(eachRow);
        }
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

    public static void LoadSearchedCarTable(DefaultTableModel table, ArrayList<Car> carList) {
        for (int i = 0; i < carList.size(); i++) {
            //get object
            Car carObj = carList.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{carObj.GetCarPlate(), carObj.GetCarType(), Integer.toString(carObj.GetPrice()), carObj.GetColor()};
            table.addRow(eachRow);
        }
    }

    public static void LoadBookingTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookObj = DataIO.allBookings.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookObj.getCustomer().getUsername(), bookObj.getCar().GetCarPlate(), bookObj.getStartDate(), bookObj.getEndDate(), Integer.toString(bookObj.getDuration()), Integer.toString(bookObj.getPayment()), bookObj.getBookStatus(), bookObj.getPaymentStatus()};
            table.addRow(eachRow);
        }
    }

    public static void LoadSearchedBookingTable(DefaultTableModel table, ArrayList<Booking> bookingList) {
        for (int i = 0; i < bookingList.size(); i++) {
            //get object
            Booking bookObj = bookingList.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookObj.getCustomer().getUsername(), bookObj.getCar().GetCarPlate(), bookObj.getStartDate(), bookObj.getEndDate(), Integer.toString(bookObj.getDuration()), Integer.toString(bookObj.getPayment()), bookObj.getBookStatus(), bookObj.getPaymentStatus()};
            table.addRow(eachRow);
        }
    }

}
