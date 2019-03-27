package models;

public class MovieRowModel {

    private String mName;
    private String mImg;
    private String mID;

    public MovieRowModel(String mName, String mImg, String mID){
        this.mName = mName;
        this.mImg = mImg;
        this.mID = mID;
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
