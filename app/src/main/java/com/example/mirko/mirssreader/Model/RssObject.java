package com.example.mirko.mirssreader.Model;

import java.util.List;

public class RssObject {

    public Feed feed;
    public List<Item>  items;

    public RssObject(Feed feed, List<Item> items) {
        this.feed = feed;
        this.items = items;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
