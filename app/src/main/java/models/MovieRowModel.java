package models;

public class MovieRowModel {

    private String mName;
    private String mImg;
    private String mID;

    MovieRowModel(String mName, String mImg, String mID){
        this.mName = mName;
        this.mImg = mImg;
        this.mID = mID;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public String getmImg() {
        return mImg;
    }

    public String getmID() {
        return mID;
    }
}
