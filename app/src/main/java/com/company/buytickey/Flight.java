package com.company.buytickey;

public class Flight {
    //Модель данных полета
    private String price,departure,landing,from,to;
    private String image;
    private String id;

    public Flight(){

    }

    public Flight(String name, String departure, String landing, String from, String to, String image, String id) {
        this.price = name;
        this.departure = departure;
        this.landing = landing;
        this.from = from;
        this.to = to;
        this.image = image;
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String name) {
        this.price = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
