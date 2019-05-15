package com.example.bei_i_bei.everywheretrip.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FocusBean {


    @Id(autoincrement = true)
    private long id;

    @Property
    private String name;
    @Property
    private String location;
    @Property
    private String photo;
    @Property
    private int following;
    @Property
    private String occupation;
    @Generated(hash = 934736149)
    public FocusBean(long id, String name, String location, String photo,
            int following, String occupation) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.photo = photo;
        this.following = following;
        this.occupation = occupation;
    }
    @Generated(hash = 552043831)
    public FocusBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public int getFollowing() {
        return this.following;
    }
    public void setFollowing(int following) {
        this.following = following;
    }
    public String getOccupation() {
        return this.occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    
}
