package carrentalsystem;

import static carrentalsystem.Global.ReadFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leere
 */
public class Customer extends Global {
    //check login status for admin
    public static Boolean CheckLoginCustomer(String FileName, String CUsernameInput, String CPasswordInput) {
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
            if (splited[0].equals(CUsernameInput)) {
                //check password
                if (splited[1].equals(CPasswordInput)) {
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
