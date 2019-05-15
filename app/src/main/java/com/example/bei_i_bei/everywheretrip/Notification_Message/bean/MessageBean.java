package com.example.bei_i_bei.everywheretrip.Notification_Message.bean;

public class MessageBean {

    private String systematic_notification;
    private String time;
    private String reply;

    public MessageBean() {
        super();
    }

    public MessageBean(String systematic_notification, String time, String reply) {
        this.systematic_notification = systematic_notification;
        this.time = time;
        this.reply = reply;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "systematic_notification='" + systematic_notification + '\'' +
                ", time='" + time + '\'' +
                ", reply='" + reply + '\'' +
                '}';
    }
}
