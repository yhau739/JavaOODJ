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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Global {

    //add global variables here for profile
    public static String Username;
    public static String Privilege;
    public static String Password;

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

    public static void SaveLoginRecord() {
        try {
            //create writer
            FileWriter writer = new FileWriter("logins.txt", true);
            
            //get current time
            LocalTime time = LocalTime.now();
//            System.out.println("Formatted time: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            
            //get current date
            LocalDate date = LocalDate.now();
//            System.out.println(date.toString());
            
            //get datetime
//            String dateTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + date.toString();
            
            //write
            writer.write(date + " " + time + " " + Username + " " + Privilege);
            writer.write(System.getProperty("line.separator"));
            writer.close();
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
            System.out.println("ReadFile error occurred.");
            e.printStackTrace();
        }
        return list;
    }
}
