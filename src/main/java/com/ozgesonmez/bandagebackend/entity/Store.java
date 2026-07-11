package com.ozgesonmez.bandagebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "bank_account")
    private String bankAccount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Store() {
    }

    public Store(Long id, String name, String phone, String taxNo, String bankAccount, AppUser user) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.taxNo = taxNo;
        this.bankAccount = bankAccount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public AppUser getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}