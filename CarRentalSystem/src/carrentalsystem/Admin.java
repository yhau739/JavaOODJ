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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
//inheritance
public class Admin extends Global {

    public static int EditorSelectedRowIndex;

    //constructor
    static {
        Admin.Privilege = "admin";
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
            JOptionPane.showMessageDialog(null, "Modification is successful");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in EditCar");
            return false;
        }
    }

    public static Boolean EditCar(int SelectedRowIndex, String CarPlate, String CarType, String Price, String CarColor) {
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
                    //take in the new inputs to replace old data
                    splited = new String[]{CarPlate, CarType, Price, CarColor};
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
            JOptionPane.showMessageDialog(null, "Modification is successful");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in EditCar");
            return false;
        }
    }

    public static Boolean AddNewAdmin(String NewUsername, String NewPassword) {
        try {
            //create writer
            FileWriter writer = new FileWriter("admin.txt", true);

            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();

            //write
            writer.write(NewUsername + " " + NewPassword + " " + date + " " + time);
            writer.write(System.getProperty("line.separator"));
            writer.close();
            JOptionPane.showMessageDialog(null, "New Admin is successfully added!", "New Admin", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in AddNewAdmin");
            return false;
        }
    }

    //check login status for admin
    public static Boolean CheckLoginAdmin(String FileName, String UsernameInput, String PasswordInput) {
        ArrayList<String> list = new ArrayList<String>();
        //read file function
        list = ReadFile(FileName);

        //loop in the size of the list (number of admins)
        for (int i = 0; i < list.size(); i++) {
            //get full line
            String fullLine = list.get(i);

            //split each full line to get credentials
            String[] splited = fullLine.split("\\s+");

            //check username
            if (splited[0].equals(UsernameInput)) {
                //check password
                if (splited[1].equals(PasswordInput)) {
                    //prompt message
                    JOptionPane.showMessageDialog(null, "Login is Successfull!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
        }
        //prompt message
        JOptionPane.showMessageDialog(null, "Incorrect credentials\n Please Try Again!", "Error Message", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
