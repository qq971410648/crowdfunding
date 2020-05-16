package com.crowdfunding.domain;


/**
 *  ssm书练习多表查询
 */
public class Person {
    private int id;
    private String name;
    private int age;
    private String sex;
    private IdCard card;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public IdCard getCard() {
        return card;
    }

    public void setCard(IdCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", card=" + card +
                '}';
    }

    public Person() {
    }

    public Person(int id, String name, int age, String sex, IdCard card) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.card = card;
    }
}
