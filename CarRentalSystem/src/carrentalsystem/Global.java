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

    //shared variables
    public static String Username;
    public static String Privilege;
    private static String Password;

    //Encapsulation    
    public static String GetPassword(){
        return Password;
    }
    
    public static void SetPassword(String password){
        Password = password;
    }
    
    //save for both user
    public static void SaveLoginRecord() {
        try {
            //create writer
            FileWriter writer = new FileWriter("logins.txt", true);

            //get current time
            LocalTime time = LocalTime.now();

            //get current date
            LocalDate date = LocalDate.now();

            //write
            writer.write(date + " " + time + " " + Username + " " + Privilege);
            writer.write(System.getProperty("line.separator"));
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in saveloginrecord");
        }
    }

    public static String[] RemoveElementFromArray(String[] arr, int index) {
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        String[] anotherArray = new String[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
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
}
