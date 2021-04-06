package com.icommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.icommerce.model.User;

/**
 *
 * @author kaiser
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
