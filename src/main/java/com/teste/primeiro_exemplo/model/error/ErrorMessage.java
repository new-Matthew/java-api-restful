package com.teste.primeiro_exemplo.model.error;

public class ErrorMessage {

    private String title;
    
    private Integer status;
    
    private String message;

    
    // objeto de erro personalizado para retornar
    public ErrorMessage(String title, Integer status, String message) {
        this.title = title;
        this.status = status;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
