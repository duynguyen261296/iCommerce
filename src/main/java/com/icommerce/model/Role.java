package com.icommerce.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.icommerce.model.enums.RoleUser;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Integer roleId;

    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public Role() {
    }

    public Role(RoleUser role) {
        this.roleUser = role;
    }
}
