package com.example.dogpaw;

public class Dog {
    private String name;
    private Integer noofdogs;
    private String breed;
    private Integer size;
    private String sitter;
    private Integer days;

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoofdogs() {
        return noofdogs;
    }

    public void setNoofdogs(Integer noofdogs) {
        this.noofdogs = noofdogs;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSitter() {
        return sitter;
    }

    public void setSitter(String sitter) {
        this.sitter = sitter;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
