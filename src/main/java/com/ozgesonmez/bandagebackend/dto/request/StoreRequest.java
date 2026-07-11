package com.ozgesonmez.bandagebackend.dto.request;

public class StoreRequest {

    private String name;
    private String phone;
    private String tax_no;
    private String bank_account;

    public StoreRequest() {
    }

    public StoreRequest(String name, String phone, String tax_no, String bank_account) {
        this.name = name;
        this.phone = phone;
        this.tax_no = tax_no;
        this.bank_account = bank_account;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getTax_no() {
        return tax_no;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
}