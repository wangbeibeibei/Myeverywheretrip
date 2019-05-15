package com.example.bei_i_bei.everywheretrip.Notification_Message.bean;

public class MessageShowBean {
    private int type;
    private String time;
    private String message;

    public MessageShowBean() {
        super();
    }

    public MessageShowBean(int type, String time, String message) {
        this.type = type;
        this.time = time;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        return "MessageShowBean{" +
                "type=" + type +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
