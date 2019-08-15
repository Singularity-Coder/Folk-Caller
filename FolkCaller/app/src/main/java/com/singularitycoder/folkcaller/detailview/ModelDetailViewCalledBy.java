package com.singularitycoder.folkcaller.detailview;

public class ModelDetailViewCalledBy {

    private int profileImage;
    private String name;
    private String dateTime;

    public ModelDetailViewCalledBy(int profileImage, String name, String dateTime) {
        this.profileImage = profileImage;
        this.name = name;
        this.dateTime = dateTime;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
