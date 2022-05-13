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
    private String ID;
    @SerializedName("name")
    private String track_name;
    private String login;
    @SerializedName("company")
    private String gp_name;
    private String location;
    @SerializedName("category")
    private String track_type;
    @SerializedName("size")
    private int track_length;
    private int cost;
    private Auxdata auxdata;

    public String getID() {
        return ID;
    }

    public String getTrackName() {
        return track_name;
    }

    public String getLogin() {
        return login;
    }

    public String getGpName() {
        return gp_name;
    }

    public String getLocation() {
        return location;
    }

    public String getTrackType() {
        return track_type;
    }

    public int getTrackLength() {
        return track_length;
    }

    public int getCost() {
        return cost;
    }

    public Auxdata getAuxdata() {
        return auxdata;
    }
}
