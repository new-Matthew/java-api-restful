package com.teste.primeiro_exemplo.view.model;

public class ProductRequest { // front/user vai mandar para o back

    //#region Atributes

    private String name;

    private Integer amount;

    private Double value;

    private String note;
    //#endregion

    //#region Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    //#endregion 
}
