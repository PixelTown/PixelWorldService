package com.pixelworld.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BladeInShine on 15/12/26.
 */
@Document(collection = "forge_recipe")
public class ForgeRecipe implements Serializable{

    @Id
    private String id;

    private String name;
    private List<String> items;
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForgeRecipe that = (ForgeRecipe) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "ForgeRecipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", available='" + available + '\'' +
                '}';
    }
}
