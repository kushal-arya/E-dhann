package com.example.project;

public class uploadinfo1 {
    public String imageName;
    public String imageURL;

    public uploadinfo1(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
    }

    public uploadinfo1() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
