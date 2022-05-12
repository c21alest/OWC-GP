package com.example.formelbilstvlingar;

/*
[
  {
    "id": "bana-laguna_seca",
    "name": "WeatherTech Raceway Laguna Seca",
    "login": "c21alest",
    "company": "Firestone Grand Prix of Monterey",
    "location": "Monterey, Kalifornien, USA",
    "category": "racebana",
    "size": 3602,
    "cost": 0,
    "auxdata": {
      "img": "https://en.wikipedia.org/wiki/WeatherTech_Raceway_Laguna_Seca#/media/File:Laguna_Seca.svg",
      "ow21": "Josef Newgarden"
    }
    }
 */

import com.google.gson.annotations.SerializedName;

public class gp {
    private String id;
    @SerializedName("track_name")
    private String name;
    private String login;
    @SerializedName("gp_name")
    private String company;
    private String location;
    @SerializedName("track_type")
    private String category;
    @SerializedName("track_length")
    private int size;
    private int cost;
    private Auxdata auxdata;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public Auxdata getAuxdata() {
        return auxdata;
    }
}
