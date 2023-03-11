package com.marvel.core.models;

public class FirstServletResponse {

    private String name;
    private int age;

    public FirstServletResponse(String name, int age) {
        this.name = name;
        this.age = age;
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
}
