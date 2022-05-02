package com.example.homesc;

public class reviewItem {
    private String itemUserName;
    private float itemRating;
    private String itemReview;

    public reviewItem(String userName, float rating, String vendName)
    {
        this.itemUserName =userName;
        this.itemRating=rating;
        this.itemReview =vendName;

    }
    public void setItemUserName(String itemUserName)
    {
        this.itemUserName = itemUserName;
    }
    public void setApptAddr(float rating)
    {
        this.itemRating=rating;
    }
    public void setItemReview(String itemReview)
    {
        this.itemReview = itemReview;
    }

    public String getItemUserName()
    {
        return itemUserName;
    }
    public float getitemRating()
    {
        return itemRating;
    }
    public String getItemReview()
    {
        return itemReview;
    }

}
