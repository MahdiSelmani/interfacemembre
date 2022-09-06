/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mahdi
 */
public class Game {

    private int id;
    private String game_name;
    private String description;
    private int categorie_id;
    private String type;

    public Game() {
    }

    public Game(String game_name, String description, int categorie_id) {
        this.game_name = game_name;
        this.description = description;
        this.categorie_id = categorie_id;
    }

    public Game(int id, String game_name, String description, int categorie_id) {
        this.id = id;
        this.game_name = game_name;
        this.description = description;
        this.categorie_id = categorie_id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
