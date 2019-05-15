package com.example.bei_i_bei.everywheretrip.Notification_Message.bean;

public class NotificationBean {

    private String systematic_notification;
    private String time;
    private String message;

    public NotificationBean() {
        super();
    }

    public NotificationBean(String systematic_notification, String time, String message) {
        this.systematic_notification = systematic_notification;
        this.time = time;
        this.message = message;
    }

    public String getSystematic_notification() {
        return systematic_notification;
    }

    public void setSystematic_notification(String systematic_notification) {
        this.systematic_notification = systematic_notification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationBean{" +
                "systematic_notification='" + systematic_notification + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
