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
public class Order {
    private int id, owner;
    private boolean delivered ;
    private String  adresse, image;
    private double price ;
    private Date date ;

    public Order() {
        
    }

    public int getId() {
        return id;
    }

    public boolean isDelivered() {
        return delivered;
    }

   

    public String getAdresse() {
        return adresse;
    }

    public int getOwner() {
        return owner;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", delivered=" + delivered + ", adresse=" + adresse + ", owner=" + owner + ", image=" + image + ", price=" + price + ", date=" + date + '}';
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOwner(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
