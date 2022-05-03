package com.example.homesc;

public class reviewItem {
    private float itemRating;
    private String itemReview;

    public reviewItem(float rating, String comment)
    {
        this.itemRating=rating;
        this.itemReview =comment;

    }
    public void setItemRating(float rating)
    {
        this.itemRating=rating;
    }
    public void setItemReview(String itemReview)
    {
        this.itemReview = itemReview;
    }
    public float getItemRating()
    {
        return this.itemRating;
    }
    public String getItemReview()
    {
        return this.itemReview;
    }

}
