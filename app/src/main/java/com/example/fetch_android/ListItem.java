package com.example.fetch_android;

public class ListItem {
    private int id;
    private int listId;
    private String name;

    public ListItem(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }
}
