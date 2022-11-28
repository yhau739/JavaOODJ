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
//abstract class
abstract public class User {

    private String Username;
    public String Privilege;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPrivilege() {
        return Privilege;
    }

    //abstract method
    abstract public void setPrivilege();

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
