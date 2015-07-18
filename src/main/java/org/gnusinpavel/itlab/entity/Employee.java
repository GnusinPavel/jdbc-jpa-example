package org.gnusinpavel.itlab.entity;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private int age;
    private boolean gender;

    public Employee() {
    }

    public Employee(String name, String surname, int age, boolean gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return gender;
    }

    public void setMale(boolean gender) {
        this.gender = gender;
    }
}
