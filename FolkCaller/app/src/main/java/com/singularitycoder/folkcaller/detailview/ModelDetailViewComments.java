package com.singularitycoder.folkcaller.detailview;

public class ModelDetailViewComments {

    private int profileImage;
    private String name;
    private String dateTime;
    private String comment;

    public ModelDetailViewComments(int profileImage, String name, String dateTime, String comment) {
        this.profileImage = profileImage;
        this.name = name;
        this.dateTime = dateTime;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
