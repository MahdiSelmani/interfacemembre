/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Mahdi
 */
public class Product {
    
    private int id , user_id, quantity ;
    private String name, description, image ;
    private Date create_at ;
    private double price ;
    private String Owner;

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String firstname, String lastname) {
        this.Owner = firstname+" "+lastname;
    }
    
    public Product(){
        this.image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3ccbwlG52iFSMA3EU6xjRmNfwTvvrk2Ya7Q&usqp=CAU" ;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", user_id=" + user_id + ", quantity=" + quantity + ", name=" + name + ", description=" + description + ", image=" + image + ", create_at=" + create_at + ", price=" + price + '}';
    }
    
    
}
