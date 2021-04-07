package com.icommerce.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.icommerce.model.UserRole;

/**
 *
 * @author kaiser
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query(value = "select u.role from user_roles u where u.username = :username", nativeQuery = true)
    Set<String> findRoleByUsername(String username);
}
