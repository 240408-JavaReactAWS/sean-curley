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
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    //No-args constructor needed for Jackson databind
    public Item() {
    }

    //All-args constructor
    public Item(int itemId, String name, boolean biggerThanBreadBox, User user) {
        this.itemId = itemId;
        this.name = name;
        this.biggerThanBreadBox = biggerThanBreadBox;
        this.user = user;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
