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
    public static String GetPassword() {
        return Password;
    }

    public static void SetPassword(String password) {
        Password = password;
    }

    public static void LoadTable(DefaultTableModel table, String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        //read file
        list = Global.ReadFile(fileName);

        for (int i = 0; i < list.size(); i++) {
            //get each full line first
            String fullLine = list.get(i);
            //each full line is split by " " to convert to []
            String[] splited = fullLine.split("\\s+");

            table.addRow(splited);
        }
    }

    public static Boolean AlreadyExists(String fileName, String userInput, String bannerMsg, int indexToCheck, Boolean SkipRow) {
        ArrayList<String> list = new ArrayList<String>();
        //read file function
        list = ReadFile(fileName);

        //loop in the size of the list (number of records)
        for (int i = 0; i < list.size(); i++) {
            //get full line
            String fullLine = list.get(i);

            //split each full line to get credentials
            String[] splited = fullLine.split("\\s+");

            if (SkipRow == true) {
                //skip the original line to avoid confusion of repetition
                if (i == Admin.EditorSelectedRowIndex) {
                    System.out.println("I skipped " + i);
                    continue;
                }
            }

            //only check the index given
            if (userInput.equals(splited[indexToCheck])) {
                JOptionPane.showMessageDialog(null, "This " + bannerMsg + " already exists! \n Please Enter a different " + bannerMsg, "Already Exists", JOptionPane.ERROR_MESSAGE);
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
