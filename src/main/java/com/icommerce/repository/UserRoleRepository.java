package com.icommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.icommerce.model.User;
import com.icommerce.model.UserRole;

/**
 *
 * @author kaiser
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
