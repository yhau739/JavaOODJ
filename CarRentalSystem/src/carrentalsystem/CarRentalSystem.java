/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

/**
 *
 * @author Lenovo
 */
public class CarRentalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Read all files first
        DataIO.ReadAllFiles();
        MainFrame main = new MainFrame();
        main.setVisible(true);
    }
    
}
