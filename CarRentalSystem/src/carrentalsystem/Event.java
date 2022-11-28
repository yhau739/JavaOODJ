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
public class Event {
    private Admin admin;
    private String EventType;
    private String Date;
    private String Time;

    public Event(Admin admin, String EventType, String Date, String Time) {
        this.admin = admin;
        this.EventType = EventType;
        this.Date = Date;
        this.Time = Time;
    }

    
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public String getEventType() {
        return EventType;
    }

    public void setEventType(String EventType) {
        this.EventType = EventType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
    
}
