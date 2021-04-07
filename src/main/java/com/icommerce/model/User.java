package com.icommerce.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    public User() {
    }

    public User(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
    }
}
