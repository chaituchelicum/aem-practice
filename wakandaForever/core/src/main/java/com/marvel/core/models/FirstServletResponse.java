package com.marvel.core.models;

public class FirstServletResponse {

    private String name;
    private int age;

    private String isHungry;

    public FirstServletResponse(String name, int age, String isHungry) {
        this.name = name;
        this.age = age;
        this.isHungry = isHungry;
    }

    public FirstServletResponse() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String isHungry() {
        return isHungry;
    }

    public void setHungry(String hungry) {
        isHungry = hungry;
    }
}
