package com.xmnode.demo.model;

import java.io.Serializable;

/**
 * for test
 * list item model
 */
public class ListItem4Test implements Serializable{

    private String title;
    private String content;
    private String avater;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
