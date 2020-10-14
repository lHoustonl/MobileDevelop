package com.example.mobiledevelop;

public class User {
    private String name;
    private String state;
    private int age;

    public int getStateSignal() {
        return StateSignal;
    }

    public void setStateSignal(int stateSignal) {
        StateSignal = stateSignal;
    }

    private int StateSignal;

    public User(String name, String state, int age, int stateSignal) {
        this.name = name;
        this.state = state;
        this.age = age;
        StateSignal = stateSignal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
