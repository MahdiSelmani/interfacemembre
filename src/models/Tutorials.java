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
public class Tutorials {
    private int id ;
    private String video ;
    private String description ;
    private int user_id ;
    private int game_id ;
        private String Owner;
    private String game_name ;

    public Tutorials() {
    }

    public Tutorials(int id, String video, String description, int game_id) {
        this.id = id;
        this.video = video;
        this.description = description;
        this.game_id = game_id;
    }

    public Tutorials(String video, String description, int user_id, int game_id) {
        this.video = video;
        this.description = description;
        this.user_id = user_id;
        this.game_id = game_id;
    }

    public Tutorials(int id, String video, String description, int user_id, int game_id) {
        this.id = id;
        this.video = video;
        this.description = description;
        this.user_id = user_id;
        this.game_id = game_id;
    }


    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String firstname, String lastname) {
        this.Owner = firstname + " " + lastname;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
    
    
    
}
