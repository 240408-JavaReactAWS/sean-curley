package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "items")
@Component
public class Item {

    @Id //itemId is the primary key for the items table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //It should be auto-generated
    private int itemId;
    private String name;
    private boolean biggerThanBreadBox;

    private int userId;


    //No-args constructor needed for Jackson databind
    public Item() {
    }

    //All-args constructor
    public Item(int itemId, String name, boolean biggerThanBreadBox, int userId) {
        this.itemId = itemId;
        this.name = name;
        this.biggerThanBreadBox = biggerThanBreadBox;
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isBiggerThanBreadBox() {
        return biggerThanBreadBox;
    }

    public void setBiggerThanBreadBox(boolean biggerThanBreadBox) {
        this.biggerThanBreadBox = biggerThanBreadBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getBiggerThanBreadBox() {
        return this.biggerThanBreadBox;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", biggerThanBreadBox=" + biggerThanBreadBox +
                '}';
    }
}
