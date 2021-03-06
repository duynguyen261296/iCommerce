package com.icommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.icommerce.model.User;
import com.icommerce.repository.UserRepository;
import com.icommerce.repository.UserRoleRepository;

/**
 *
 * @author kaiser
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        Set<String> userRoles = userRoleRepository.findRoleByUsername(userName);
        if (!userRoles.isEmpty()) {
            userRoles.forEach(userRole -> {
                // ROLE_USER, ROLE_ADMIN
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole);
                grantList.add(authority);
            });
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);
    }
}
