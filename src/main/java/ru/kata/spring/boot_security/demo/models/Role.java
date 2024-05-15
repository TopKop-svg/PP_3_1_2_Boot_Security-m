package ru.kata.spring.boot_security.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    public Role() {
    }

    public Role(String name) {
        this.role = name;
    }

    public Role(int id, String name) {
        this.id = id;


        this.role = name;
    }

    @Override
    public String toString() {
        return getRole().replace("ROLE_", "");
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
