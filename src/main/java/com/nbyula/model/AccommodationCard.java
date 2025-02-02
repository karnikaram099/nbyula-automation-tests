package com.nbyula.model;

public class AccommodationCard {
    private String title;
    private String price;
    private String link;

    public AccommodationCard(String title, String price, String link) {
        this.title = title;
        this.price = price;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nPrice: " + price + "\nLink: " + link + "\n-----------------------------";
    }
}