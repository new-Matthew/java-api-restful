package com.teste.primeiro_exemplo.model;
import jakarta.persistence.Entity; // javax virou jakarta
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// através do hibernate faz entender que tem q pegar a classe produto e transformar em tabela no bd
@Entity
public class Product {
    //#region Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // estratégia de atualização pelo hibernate implementa
    private Integer id;

    private String name;

    private Integer amount;

    private Double value;

    private String note;
    //#endregion

    //#region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
