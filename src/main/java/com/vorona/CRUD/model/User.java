package com.vorona.CRUD.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Entity
@Table(name = "usersssss")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Вы ничего не ввели")
    @Size(min = 3, max = 20,message = "Имя должно быть в диапазоне от 2 до 20 символов")
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "Вы ничего не ввели")
    @Size(min = 3, max = 20,message = "Имя должно быть в диапазоне от 2 до 20 символов")
    private String surname;
    @Min(value = 0,message = "Возраст должен быть больш нуля")
    @Column(name = "age")
    private int age;



    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
