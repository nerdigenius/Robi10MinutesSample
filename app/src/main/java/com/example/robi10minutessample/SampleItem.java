package com.example.robi10minutessample;

import java.util.ArrayList;

public class SampleItem {
    private int imageResource;
    private String mtext1;
    private boolean chapter;
    private boolean expanded;
    private ArrayList<ChildItems> childItemsArrayList;
    private String type;
    private String author;

    public SampleItem(int imageResource, String type, String mtext1, boolean chapter, ArrayList<ChildItems> childItemsArrayList) {
        this.imageResource = imageResource;
        this.mtext1 = mtext1;
        this.type=type;
        this.chapter = chapter;
        this.childItemsArrayList=childItemsArrayList;

    }

    public String getAuthor() {
        return author;
    }

    public SampleItem(int imageResource, String type, String mtext1, boolean chapter, ArrayList<ChildItems> childItemsArrayList, String author) {
        this.imageResource = imageResource;
        this.mtext1 = mtext1;
        this.chapter = chapter;
        this.childItemsArrayList = childItemsArrayList;
        this.type = type;
        this.author = author;
    }

    public boolean isChapter() {
        return chapter;
    }

    public String getType() {
        return type;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getMtext1() {
        return mtext1;
    }

    public ArrayList<ChildItems> getchildItemsArrayList() {
        return childItemsArrayList;
    }


}
