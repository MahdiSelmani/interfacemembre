/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HO
 */
public class Game_categoeries {
    int id ;
    String type ;
    
    public Game_categoeries() {
    }
    
    public Game_categoeries(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Game_categoeries(String type) {
        this.type = type;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Game_categoeries{" + "id=" + id + ", type=" + type + '}';
    }
    
    
}
