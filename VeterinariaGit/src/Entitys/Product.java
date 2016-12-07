/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mannu
 */
public abstract class Product implements Serializable {
    private long id;
    private String name;
    private int amount;
    private double cost;
    private Date expiration_date;

    public Product() {
    }

    public Product(String name, int amount, double cost, Date expiration_date) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
        this.expiration_date = expiration_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


  
}
