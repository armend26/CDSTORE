package model;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = -5083759422249745403L;
    private String user;
    private String pass;
    private final int id;
    public static int x=0;
    private int level;//1-Admin, 2-Editor, 3-Normal User

    public User() {
        this(x++, "","",3);
    }
    public User(int id, String user, String pass, int level) {
        super();

        this.id=id;
        if(x<id) x=id;
        this.user = user;
        this.pass = pass;
        this.level = level;
    }
    public int getid() {
        return this.id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

}

