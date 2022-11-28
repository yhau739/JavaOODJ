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
public class Login {
    private String LoginDate;
    private String LoginTime;
    private String LoginUsername;
    private String privillege;

    public Login(String LoginDate, String LoginTime, String LoginUsername, String privillege) {
        this.LoginDate = LoginDate;
        this.LoginTime = LoginTime;
        this.LoginUsername = LoginUsername;
        this.privillege = privillege;
    }

    public String getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(String LoginDate) {
        this.LoginDate = LoginDate;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String LoginTime) {
        this.LoginTime = LoginTime;
    }

    public String getLoginUsername() {
        return LoginUsername;
    }

    public void setLoginUsername(String LoginUsername) {
        this.LoginUsername = LoginUsername;
    }

    public String getPrivillege() {
        return privillege;
    }

    public void setPrivillege(String privillege) {
        this.privillege = privillege;
    }


}
