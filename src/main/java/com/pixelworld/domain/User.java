package com.pixelworld.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by BladeInShine on 15/12/13.
 */
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private String id;

    private String username;
    private int lv;
    private int atk;
    private int def;
    private int hp;
    private String head;
    private String armor;
    private String boot;
    private String glove;
    private String left;
    private String right;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getBoot() {
        return boot;
    }

    public void setBoot(String boot) {
        this.boot = boot;
    }

    public String getGlove() {
        return glove;
    }

    public void setGlove(String glove) {
        this.glove = glove;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", lv=" + lv +
                ", atk=" + atk +
                ", def=" + def +
                ", hp=" + hp +
                ", head='" + head + '\'' +
                ", armor='" + armor + '\'' +
                ", boot='" + boot + '\'' +
                ", glove='" + glove + '\'' +
                ", left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
