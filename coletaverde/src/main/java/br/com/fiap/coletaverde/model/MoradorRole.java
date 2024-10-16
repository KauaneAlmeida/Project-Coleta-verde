package br.com.fiap.coletaverde.model;

public enum MoradorRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    MoradorRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}