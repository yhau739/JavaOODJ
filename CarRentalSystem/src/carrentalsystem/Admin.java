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
//    public static int EditorSelectedRowIndex;

    public void setPrivilege(){
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
    }

    public static void SaveEventLogs(Admin adminObj) {
        try {
            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();

            DataIO.allEvents.add(new Event(adminObj, adminObj.Event, date.toString(), time.toString()));
            DataIO.WriteFileEvent();
        } catch (Exception e) {
            System.out.println("Failed to save event logs");
        }
    }

    public static Boolean AddCar(String CarPlate, String CarType, String Price, String color) {
        try {
            //create obj
            Car newCar = new Car(CarPlate, CarType, Integer.parseInt(Price), color);
            //add obj
            DataIO.allCars.add(newCar);
            //write file
            DataIO.WriteFileCar();
            JOptionPane.showMessageDialog(null, "New Car is successfully added!", "New Car", JOptionPane.INFORMATION_MESSAGE);
            Global.CurrentAdmin.setEvent("AddNewCar");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "failed to add car!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
//        try {
//            //create writer
//            FileWriter writer = new FileWriter("cars.txt", true);
//
//            //write
//            writer.write(CarPlate + " " + CarType + " " + Price + " " + color);
//            writer.write(System.getProperty("line.separator"));
//            writer.close();
//            JOptionPane.showMessageDialog(null, "New Car is successfully added!", "New Car", JOptionPane.INFORMATION_MESSAGE);
////            Admin.Event = "AddCar";
//            return true;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "failed to add car!", "Error", JOptionPane.INFORMATION_MESSAGE);
//            return false;
//        }
    }

    public static Car SearchCar(String CarPlate) {
        for(Car i :DataIO.allCars){
            if(i.GetCarPlate().equals(CarPlate)){
                return i;
            }
        }
        return null;
//        ArrayList<String> list = new ArrayList<String>();
//        //read file function
//        list = ReadFile("cars.txt");
//
//        for (int i = 0; i < list.size(); i++) {
//            //get each full line first
//            String fullLine = list.get(i);
//            //split
//            String[] splited;
//            splited = fullLine.split("\\s+");
//            //if matching records are found
//            if (splited[0].equals(CarPlate)) {
//                //for delete car section
//                Admin.EditorSelectedRowIndex = i;
//                Car.CarPlate = splited[0];
//                Car.CarType = splited[1];
//                Car.PricePerDay = Integer.parseInt(splited[2]);
//                Car.CarColor = splited[3];
//                JOptionPane.showMessageDialog(null, "Matching Record is found");
//                return true;
//            }
//        }
//        JOptionPane.showMessageDialog(null, "No Matching Records");
    }

    public static Boolean DeleteCar(int SelectedRowIndex) {
        ArrayList<String> list = new ArrayList<String>();
        //read file function
        list = ReadFile("cars.txt");

        try {
            FileWriter writer = new FileWriter("cars.txt", false);
            //for each line
            for (int i = 0; i < list.size(); i++) {
                //get each full line first
                String fullLine = list.get(i);
                String[] splited;

                //to filter row to edit
                if (i == SelectedRowIndex) {
                    continue;
                } else {
                    //each full line is split by " " to convert to []
                    splited = fullLine.split("\\s+");
                }

                //loop & write each word into it
                for (int x = 0; x < splited.length; x++) {
                    writer.write(splited[x] + " ");
                }
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            JOptionPane.showMessageDialog(null, "Deletion is successful");
//            Admin.Event = "DeleteCar";
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in DeleteCar");
            return false;
        }
    }

    public static Boolean EditCar(String CarPlate, String CarType, String Price, String CarColor) {
        Boolean inputValidation = (!CarPlate.equals("") && !CarType.equals("") && !Price.equals("") && !CarColor.equals(""));
//        System.out.println(inputValidation);
        if(!inputValidation){
            JOptionPane.showMessageDialog(null, "Please don't leave any text field empty");
            return false;
        }
        //to check existing carplate
        int found = 0;

        for (Car i : DataIO.allCars) {
            //Car found
            if (i.GetCarPlate().equals(CarPlate)) {
                //validation existing car plate
                for (Car j : DataIO.allCars) {
                    if (i.GetCarPlate().equals(j.GetCarPlate())) {
                        found = found + 1;
                    }
                }
                //validation Integer input
                Boolean isInt = Global.IsInteger(Price, "Price");
                Boolean isStr1 = Global.StringOnly(CarType, "Car Type");
                Boolean isStr2 = Global.StringOnly(CarColor, "Car Color");
                System.out.println(isInt + " " + isStr1 + " " + isStr2 + " "+ found);
                //pass validation
                if (found == 1 && isInt && isStr1 && isStr2) {
                    //Edit values
                    i.setCarPlate(CarPlate);
                    i.setCarType(CarType);
                    i.setPricePerDay(Integer.parseInt(Price));
                    i.setCarColor(CarColor);
//                    System.out.println(DataIO.allCars.get(0).GetCarType());
                    //Write to File
                    DataIO.WriteFileCar();
                    System.out.println("HERE");
                    Global.CurrentAdmin.setEvent("AddNewAdmin");
                    JOptionPane.showMessageDialog(null, "Modification is successful!");
                    return true;
                } else if (found == 2) {
                    JOptionPane.showMessageDialog(null, "This Car Plate is already taken!");
                } else if (!isInt) {
                    JOptionPane.showMessageDialog(null, "Price must be in numbers!");
                } else if (!isStr1){
                    JOptionPane.showMessageDialog(null, "Car Type must be in letters!");
                } else if (!isStr2){
                    JOptionPane.showMessageDialog(null, "Car color must be in letters!");
                }
            }
        }
        return false; 
    }

//    public static Boolean EditCar(int SelectedRowIndex, String CarPlate, String CarType, String Price, String CarColor) {
//        ArrayList<String> list = new ArrayList<String>();
//        //read file function
//        list = ReadFile("cars.txt");
//
//        try {
//            FileWriter writer = new FileWriter("cars.txt", false);
//            //for each line
//            for (int i = 0; i < list.size(); i++) {
//                //get each full line first
//                String fullLine = list.get(i);
//                String[] splited;
//
//                //to filter row to edit
//                if (i == SelectedRowIndex) {
//                    //take in the new inputs to replace old data
//                    splited = new String[]{CarPlate, CarType, Price, CarColor};
//                } else {
//                    //each full line is split by " " to convert to []
//                    splited = fullLine.split("\\s+");
//                }
//
//                //loop & write each word into it
//                for (int x = 0; x < splited.length; x++) {
//                    writer.write(splited[x] + " ");
//                }
//                writer.write(System.getProperty("line.separator"));
//            }
//            writer.flush();
//            writer.close();
//            JOptionPane.showMessageDialog(null, "Modification is successful");
////            Admin.Event = "EditCar";
//            return true;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error in EditCar");
//            return false;
//        }
//    }

    public static Boolean AddNewAdmin(String NewUsername, String NewPassword) {
        try {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            //add data
            DataIO.allAdmins.add(new Admin(NewUsername, NewPassword, date.toString(), time.toString()));
            //after insert, write
            DataIO.WriteFileAdmin();
            JOptionPane.showMessageDialog(null, "New Admin is successfully added!", "New Admin", JOptionPane.INFORMATION_MESSAGE);
            Global.CurrentAdmin.setEvent("AddNewAdmin");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in AddNewAdmin");
            return false;
        }
    }

    //check login status for admin
    public static Admin CheckLoginAdmin(String UsernameInput, String PasswordInput) {
        Admin existAdmin = null;
        for (Admin adminObj : DataIO.allAdmins) {
            if (adminObj.getUsername().equals(UsernameInput)) {
                if (adminObj.getPassword().equals(PasswordInput)) {
                    //prompt message
                    JOptionPane.showMessageDialog(null, "Login is Successfull!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    //return the user so that we know the current user
                    existAdmin = adminObj;
                    //Set up profile
                    Global.CurrentAdmin = existAdmin;

                    return existAdmin;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Incorrect credentials\n Please Try Again!", "Error Message", JOptionPane.ERROR_MESSAGE);
        return existAdmin;
    }

    public static void LoadAdminTable(DefaultTableModel table) {
        for (int i = 0; i < DataIO.allAdmins.size(); i++) {
            //get object
            Admin adminObj = DataIO.allAdmins.get(i);
            //get values and make it into an array
            String[] eachRow = new String[]{adminObj.getUsername(), adminObj.getCreatedOnDate(), adminObj.getCreatedOnTime()};

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

}
