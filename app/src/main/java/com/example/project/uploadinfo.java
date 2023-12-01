package com.example.project;

public class uploadinfo {
    public String imageName;
    public String imageURL;
    public  String loc;
    public uploadinfo(){}

    public uploadinfo(String imageName, String imageURL, String loc) {
        this.imageName = imageName;
        this.imageURL = imageURL;
        this.loc = loc;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }



    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "uploadinfo{" +
                "imageName='" + imageName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
