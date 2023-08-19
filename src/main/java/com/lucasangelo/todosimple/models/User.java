package com.lucasangelo.todosimple.models;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "user";

    public interface createUser {
    }

    public interface updateUser {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT PADRÃO MYSQL
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 50, nullable = false)
    @Size(min = 2, max = 50)
    @NotBlank
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    //*  cosntrutor vazio
    public User() {

    }

    //* Construtor com todos os campos
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    //*  GETTERS E SETTERS
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //* Getters e setters task
    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    // Verificações
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (obj == null) {
            return false;
        
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        


        if (this.id == null) {
            if (other.id != null) {
                return false;
            } else if (!this.id.equals(other.id)) {
                return false;
            }

        }
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);

    }

}
