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

    @Override
    public int hashCode() {
        int hashCode = (int) id;
        hashCode = 47 * hashCode + (name != null ? xor(name.getBytes()) : 0);
        hashCode = 47 * hashCode + (surname != null ? xor(surname.getBytes()) : 0);
        hashCode = 47 * hashCode + xor(Integer.toString(age).getBytes());
        hashCode = 47 * hashCode + xor(Boolean.toString(gender).getBytes());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (hashCode() != obj.hashCode()) {
            return false;
        } else if (obj instanceof Employee) {
            Employee e = (Employee) obj;
            return id == e.id && name.equals(e.name) && surname.equals(e.surname) && age == e.age && gender == e.gender;
        }
        return super.equals(obj);
    }

    private int xor(byte... bytes) {
        if (bytes == null) {
            return 0;
        }
        int result = 0;
        for (byte b : bytes) {
            result ^= b;
        }
        return result;
    }
}
