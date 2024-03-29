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
    public static Boolean CollectPayment(String username, String carplate) {
        for (Booking b : DataIO.allBookings) {
            if (b.getCustomer().getUsername().equals(username) && b.getCar().GetCarPlate().equals(carplate)) {
                b.setBookStatus("booked");
                b.setPaymentStatus("yes");
                DataIO.WriteFileBooking();
                Global.CurrentAdmin.setEvent("CollectPayment");
                Admin.SaveEventLogs(Global.CurrentAdmin);
                JOptionPane.showMessageDialog(null, "Payment Collected successfully!");
                return true;
            }
        }
        return false;
    }

    public static Boolean ApproveCustomer(String username) {
        for (Customer c : DataIO.allCustomers) {
            if (c.getUsername().equals(username)) {
                c.setStatus("approve");
                DataIO.WriteFileCustomer();
                Global.CurrentAdmin.setEvent("ApproveCustomer");
                Admin.SaveEventLogs(Global.CurrentAdmin);
                JOptionPane.showMessageDialog(null, "Customer approved successfully!");
                return true;
            }
        }
        return false;
    }

    public static Boolean EditCustomer(String username, String password, String gender, String age, String phone, String email, String address, String card) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(username);
        list.add(password);
        list.add(gender);
        list.add(age);
        list.add(phone);
        list.add(email);
        list.add(address);
        list.add(card);

        //password validation problem
        if (String.valueOf(password).length() < 5) {
            JOptionPane.showMessageDialog(null, "Password length must be at least 5 characters long!", "Invalid Password", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        //validation
        if (Global.NullValuesExist(list)) {
            return false;
        }

        Boolean validateAge = Customer.ValidateAge(age);
        Boolean validatePhone = Customer.ValidatePhone(phone);
        Boolean validateEmail = Customer.ValidateEmail(email);
        Boolean validateCard = Customer.ValidateCard(card);
        if (validateAge == false) { //Age wrong format, too small or too big
            JOptionPane.showMessageDialog(null, "Age is in wrong format!");
            return false;
        }

        if (validatePhone == false) { //phone null or wrong format
            JOptionPane.showMessageDialog(null, "Phone number is in wrong format!");
            return false;
        }

        if (validateEmail == false) { //email null or wrong format
            JOptionPane.showMessageDialog(null, "Email is in wrong format!");
            return false;
        }

        if (validateCard == false) { //card null or wrong format
            JOptionPane.showMessageDialog(null, "card is in wrong format!");
            return false;
        }

        if (validateAge && validatePhone && validateEmail) { //pass validation
            //edit profile
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

                    //Write to File
                    DataIO.WriteFileCustomer();
                    Global.CurrentAdmin.setEvent("EditCustomer");
                    Admin.SaveEventLogs(Global.CurrentAdmin);
                    JOptionPane.showMessageDialog(null, "Customer edited successfully!");
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean DeleteCustomer(String username) {
        for (Customer cusObj : DataIO.allCustomers) {
            if (cusObj.getUsername().equals(username)) {
                DataIO.allCustomers.remove(cusObj);
                //write file
                DataIO.WriteFileCustomer();
                Global.CurrentAdmin.setEvent("DeleteCustomer");
                Admin.SaveEventLogs(Global.CurrentAdmin);
                JOptionPane.showMessageDialog(null, "Customer is deleted successfully!");
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Customer> SearchCustomer(String customerUsername, String attribute) {
        ArrayList<Customer> MatchList = new ArrayList<Customer>();
        for (Customer i : DataIO.allCustomers) {
            switch (attribute) {
                case "Username":
                    if (i.getUsername().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Gender":
                    if (i.getGender().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Age":
                    if (i.getAge().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
                case "Status":
                    if (i.getStatus().matches("(.*)" + customerUsername + "(.*)")) {
                        MatchList.add(i);
                    }
                    break;
            }
        }
        return MatchList;
    }

    public static Boolean EditBooking(String username, String carPlate, String startDate, String Duration) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(carPlate);
        list.add(startDate);
        list.add(Duration);

        Car newcar = null;

        //validate null values
        if (Global.NullValuesExist(list)) {
            return false;
        }

        //validate if date is already passed
        Boolean validation = Global.beforeTodayDate(startDate);
        if (validation) {
            return false;
        }

        //check integer
        Boolean isInt = Global.IsInteger(Duration, "Duration");
        if (!isInt) {
            return false;
        }

        //if car has been changed
        if (!Admin.CurrentCarPlate.equals(carPlate)) {
            //check if car is taken
            for (Booking b : DataIO.allBookings) {
                if (b.getCar().GetCarPlate().equals(carPlate) && b.getBookStatus().equals("")) {
                    //car is taken, can not change to this car
                    JOptionPane.showMessageDialog(null, "This car is unavailable, please choose another one!");
                    return false;
                }
            }
            //if car not taken, check if car exist
            for (Car c : DataIO.allCars) {
                //if car exist
                if (c.GetCarPlate().equals(carPlate)) {
                    newcar = c;
                }
            }
            if (newcar == null) {
                //if car does not exist
                JOptionPane.showMessageDialog(null, "This car does not exist, please choose another one!");
                return false;
            }
        }
        for (Booking b : DataIO.allBookings) {
            if (b.getCustomer().getUsername().equals(username)) {
                if (b.getCar().GetCarPlate().equals(Admin.CurrentCarPlate)) {
                    //matching booking
                    if (newcar != null) {
                        b.setCar(newcar);
                    }
                    b.setStartDate(startDate);
                    b.setDuration(Integer.parseInt(Duration));
                    b.setEndDate(Global.addDate(startDate, Duration));
                    b.setPayment(b.getCar().GetPrice() * Integer.parseInt(Duration));
                    //Write to File
                    DataIO.WriteFileBooking();
                    //save events
                    Global.CurrentAdmin.setEvent("EditBooking");
                    Admin.SaveEventLogs(Global.CurrentAdmin);
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
            if (i.getBookStatus().equals("available") || i.getBookStatus().equals("pending") || i.getBookStatus().equals("notavailable")) {
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
        }
        return MatchList;
    }

    public static ArrayList<Booking> SearchBookingHistory(String customerUsername, String attribute) {
        ArrayList<Booking> MatchList = new ArrayList<Booking>();
        for (Booking i : DataIO.allBookings) {
            if (i.getBookStatus().equals("booked") || i.getBookStatus().equals("returned")) {
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
        }
        return MatchList;
    }

    public static Boolean DeleteBooking(String username, String carplate) {
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getCustomer().getUsername().equals(username)) {
                if (bookObj.getCar().GetCarPlate().equals(carplate)) {
                    DataIO.allBookings.remove(bookObj);
                    //write file
                    DataIO.WriteFileBooking();
                    Global.CurrentAdmin.setEvent("DeleteBooking");
                    Admin.SaveEventLogs(Global.CurrentAdmin);
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
                    //if booking approved + paid
                    if (bookObj.getBookStatus().equals("booked")) {
                        JOptionPane.showMessageDialog(null, "This booking is already approved and paid!");
                        return false;
                    } //booking approved + not paid
                    else if (bookObj.getBookStatus().equals("available")) {
                        JOptionPane.showMessageDialog(null, "This booking is already approved!");
                        return false;
                    } //completed bookings
                    else if (bookObj.getBookStatus().equals("returned")) {

                    } //booking previously disapproved
                    else if (bookObj.getBookStatus().equals("notavailable")) {
                        int reply = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to approve the previously disapproved booking?",
                                "Comfirmation",
                                JOptionPane.YES_NO_OPTION);
                        //yes
                        if (reply == JOptionPane.YES_OPTION) {
                            bookObj.setBookStatus("available");
                            //write file
                            DataIO.WriteFileBooking();
                            Global.CurrentAdmin.setEvent("ApproveBooking");
                            Admin.SaveEventLogs(Global.CurrentAdmin);
                            JOptionPane.showMessageDialog(null, "Booking is approved!");
                            return true;
                        } //no
                        else {
                            return false;
                        }
                    }
                    //pending booking
                    bookObj.setBookStatus("available");
                    //write file
                    DataIO.WriteFileBooking();
                    Global.CurrentAdmin.setEvent("ApproveBooking");
                    //Admin.SaveEventLogs(Global.currentadmin);
                    JOptionPane.showMessageDialog(null, "Booking is approved!");
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean DisapproveBooking(String username, String carplate) {
        //get Booking obj
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getCustomer().getUsername().equals(username)) {
                if (bookObj.getCar().GetCarPlate().equals(carplate)) {
                    String status = bookObj.getBookStatus();
                    switch (status) {
                        case "booked":
                            JOptionPane.showMessageDialog(null, "This booking is already approved and paid!");
                            return false;
                        case "available":
                            int reply = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to disapprove the previously approved booking?",
                                    "Comfirmation",
                                    JOptionPane.YES_NO_OPTION);
                            //yes
                            if (reply == JOptionPane.YES_OPTION) {
                                break;
                            } //no
                            else {
                                return false;
                            }
                        case "notavailable":
                            JOptionPane.showMessageDialog(null, "This booking is already disapproved!");
                            return false;
                        case "pending":
                            break;
                    }
                    //set status
                    bookObj.setBookStatus("notavailable");
                    //write file
                    DataIO.WriteFileBooking();
                    Global.CurrentAdmin.setEvent("DisapproveBooking");
                    Admin.SaveEventLogs(Global.CurrentAdmin);
                    JOptionPane.showMessageDialog(null, "Booking is disapproved!");
                    return true;
                }
            }
        }
        return false;
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
                Admin.SaveEventLogs(Global.CurrentAdmin);
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
                for (Booking b : DataIO.allBookings) {
                    if(b.getCar().GetCarPlate().equals(carplate)){
                        JOptionPane.showMessageDialog(null, "Car is in use, can't be deleted!");
                        return false;
                    }
                }
                DataIO.allCars.remove(carObj);
                DataIO.WriteFileCar();
                Global.CurrentAdmin.setEvent("DeleteCar");
                Admin.SaveEventLogs(Global.CurrentAdmin);
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
                    Admin.SaveEventLogs(Global.CurrentAdmin);
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
            Admin.SaveEventLogs(Global.CurrentAdmin);
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
                DataIO.WriteFileAdmin();
                Global.CurrentAdmin.setEvent("DeleteAdmin");
                Admin.SaveEventLogs(Global.CurrentAdmin);
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
                Admin.SaveEventLogs(Global.CurrentAdmin);
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
                    Global.SaveAdminLoginRecord(adminObj);
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
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getBookStatus().equals("available") || bookObj.getBookStatus().equals("pending") || bookObj.getBookStatus().equals("notavailable")) {
                String[] eachRow = new String[]{bookObj.getCustomer().getUsername(), bookObj.getCar().GetCarPlate(), bookObj.getStartDate(), bookObj.getEndDate(), Integer.toString(bookObj.getDuration()), Integer.toString(bookObj.getPayment()), bookObj.getBookStatus(), bookObj.getPaymentStatus()};
                table.addRow(eachRow);
            }
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

    public static void LoadBookingHistoryTable(DefaultTableModel table) {
        for (Booking bookObj : DataIO.allBookings) {
            if (bookObj.getBookStatus().equals("booked") || bookObj.getBookStatus().equals("returned")) {
                String[] eachRow = new String[]{bookObj.getCustomer().getUsername(), bookObj.getCar().GetCarPlate(), bookObj.getStartDate(), bookObj.getEndDate(), Integer.toString(bookObj.getDuration()), Integer.toString(bookObj.getPayment()), bookObj.getBookStatus(), bookObj.getPaymentStatus()};
                table.addRow(eachRow);
            }
        }
    }

    public static void LoadCustomerTable(DefaultTableModel table) {
        for (Customer c : DataIO.allCustomers) {
            String[] eachRow = new String[]{c.getUsername(), c.getPassword(), c.getGender(), c.getAge(), c.getPhone(), c.getEmail(), c.getAddress(), c.getCard(), c.getStatus()};
            table.addRow(eachRow);
        }
    }

    public static void LoadSearchedCustomerTable(DefaultTableModel table, ArrayList<Customer> CustomerList) {
        for (int i = 0; i < CustomerList.size(); i++) {
            //get object
            Customer c = CustomerList.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{c.getUsername(), c.getPassword(), c.getGender(), c.getAge(), c.getPhone(), c.getEmail(), c.getAddress(), c.getCard(), c.getStatus()};
            table.addRow(eachRow);
        }
    }

    public static void LoadComfirmCustomerTable(DefaultTableModel table) {
        for (Customer c : DataIO.allCustomers) {
            if (c.getStatus().equals("unapprove")) {
                String[] eachRow = new String[]{c.getUsername(), c.getPassword(), c.getGender(), c.getAge(), c.getPhone(), c.getEmail(), c.getAddress(), c.getCard(), c.getStatus()};
                table.addRow(eachRow);
            }
        }
    }

    public static void LoadReceiptTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allBookings.size(); i++) {
            //get object
            Booking bookingObj = DataIO.allBookings.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{bookingObj.getCustomer().getUsername(), bookingObj.getCar().GetCarPlate(), bookingObj.getCar().GetCarType(), Integer.toString(bookingObj.getCar().GetPrice()), bookingObj.getCar().GetColor(), bookingObj.getStartDate(), bookingObj.getEndDate(), Integer.toString(bookingObj.getDuration()), Integer.toString(bookingObj.getPayment())};
            String bookingStatus = bookingObj.getBookStatus();
            String paymentStatus = bookingObj.getPaymentStatus();
            if (bookingStatus.equals("available") && paymentStatus.equals("no")) {
                table.addRow(eachRow);
            }
        }
    }
}
