/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class DataIO {

    public static ArrayList<Admin> allAdmins = new ArrayList<Admin>();
    public static ArrayList<Car> allCars = new ArrayList<Car>();
    public static ArrayList<Event> allEvents = new ArrayList<Event>();
    public static ArrayList<Login> allLogins = new ArrayList<Login>();
    public static ArrayList<Customer> allCustomer = new ArrayList<Customer>();

    public static void ReadAllFiles() {
        ReadFileAdmin();
        ReadFileCar();
        ReadFileEvent();
        ReadFileLogin();
    }

    //readfile (return full line as a string), using as support method
    public static void ReadFileAdmin() {
        //read file and read each full line
        try {
            File myObj = new File("admin.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fullLine = myReader.nextLine();

                //split each full line to get credentials
                String[] splited = fullLine.split("\\s+");
                allAdmins.add(new Admin(splited[0], splited[1], splited[2], splited[3]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //for debug purpose
            JOptionPane.showMessageDialog(null, "Error in ReadFile");
        }
    }

    public static void WriteFileAdmin() {
        try {
            //create writer
            FileWriter writer = new FileWriter("admin.txt", false);

            for (Admin obj : allAdmins) {
                writer.write(obj.getUsername() + " " + obj.getPassword() + " " + obj.getCreatedOnDate() + " " + obj.getCreatedOnTime());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in WriteFileAdmin");
        }
    }

    public static void ReadFileEvent() {
        //read file and read each full line
        try {
            File myObj = new File("event.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fullLine = myReader.nextLine();

                //split each full line to get credentials
                String[] splited = fullLine.split("\\s+");
                for (Admin obj : allAdmins) {
                    if (splited[0].equals(obj.getUsername())) {
                        allEvents.add(new Event(obj, splited[1], splited[2], splited[3]));
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //for debug purpose
            JOptionPane.showMessageDialog(null, "Error in ReadFile");
        }
    }

    public static void WriteFileEvent() {
        try {
            //create writer
            FileWriter writer = new FileWriter("event.txt", false);

            for (Event obj : allEvents) {
                writer.write(obj.getAdmin().getUsername() + " " + obj.getEventType() + " " + obj.getDate() + " " + obj.getTime());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in WriteFileEvent");
        }
    }

    public static void ReadFileLogin() {
        //read file and read each full line
        try {
            File myObj = new File("logins.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fullLine = myReader.nextLine();

                //split each full line to get credentials
                String[] splited = fullLine.split("\\s+");
                allLogins.add(new Login(splited[0], splited[1], splited[2], splited[3]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //for debug purpose
            JOptionPane.showMessageDialog(null, "Error in ReadFileLogin");
        }
    }

    public static void WriteFileLogin() {
        try {
            //create writer
            FileWriter writer = new FileWriter("logins.txt", false);

            for (Login obj : allLogins) {
                writer.write(obj.getLoginDate() + " " + obj.getLoginTime() + " " + obj.getLoginUsername()+ " " + obj.getPrivillege());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in WriteFileEvent");
        }
    }

    public static void ReadFileCar() {
        //read file and read each full line
        try {
            File myObj = new File("cars.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String fullLine = myReader.nextLine();

                //split each full line to get credentials
                String[] splited = fullLine.split("\\s+");
                allCars.add(new Car(splited[0], splited[1], Integer.parseInt(splited[2]), splited[3]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //for debug purpose
            JOptionPane.showMessageDialog(null, "Error in ReadFile");
        }
    }

    public static void WriteFileCar() {
        try {
            //create writer
            FileWriter writer = new FileWriter("cars.txt", false);

            for (Car obj : allCars) {
                writer.write(obj.GetCarPlate() + " " + obj.GetCarType() + " " + obj.GetPrice() + " " + obj.GetColor());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in WriteFileCar");
        }
    }
}