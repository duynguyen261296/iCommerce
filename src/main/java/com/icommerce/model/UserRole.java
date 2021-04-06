package com.icommerce.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */
@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "role", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public UserRole() {
    }

    public UserRole(String username, RoleUser roleUser) {
        this.username = username;
        this.roleUser = roleUser;
    }
}
