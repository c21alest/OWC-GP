package com.example.formelbilstvlingar;

/*
    "auxdata": {
      "img": "https://en.wikipedia.org/wiki/WeatherTech_Raceway_Laguna_Seca#/media/File:Laguna_Seca.svg",
      "ow21": "Josef Newgarden"
    }
 */

public class Auxdata {
    private String img;
    private String ow21;
    private String dimg;
    private int age;
    private String nat;

    public String getNat() {
        return nat;
    }

    public String getDimg() {
        return dimg;
    }

    public int getAge() {
        return age;
    }

    public String getImg() {
        return img;
    }

    public String getOw21() {
        return ow21;
    }
}
