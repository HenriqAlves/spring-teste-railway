package com.lucasangelo.todosimple.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "user";

    public interface createUser {}
    public interface updateUser {}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT PADRÃO MYSQL
    @Column(name = "id", unique=true)
    private Long id;


    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 100)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    @Size(min = 2, max = 50)
    @NotBlank
    private String password;

    // private List<Task> = new ArrayList<Task>();

    //cosntrutor vazio
    public User(){

    }

    // Construtor com todos os campos
    public User(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }


    // GETTERS E SETTERS

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public boolean equals (Object o){
        if(o == this){
            return true;
        }
        if(! o instanceof User){
            return false;
        }

        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Object.equals;
    }


}
