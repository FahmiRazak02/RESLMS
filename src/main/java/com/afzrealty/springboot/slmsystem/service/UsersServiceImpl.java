package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.dao.UsersDAO;
import com.afzrealty.springboot.slmsystem.dao.RolesDAO;
import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.entity.Role;
import com.afzrealty.springboot.slmsystem.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersDAO usersDAO;
    private RolesDAO rolesDAO;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersDAO usersDAO, RolesDAO rolesDAO, BCryptPasswordEncoder passwordEncoder){
        this.usersDAO = usersDAO;
        this.rolesDAO = rolesDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByUserName(String userName) {
        // Check the database if the user already exists
        return usersDAO.findUserByUserName(userName);
    }

    @Override
    public User findUserByNric(String nric) {
        return usersDAO.findByNric(nric);
    }

    @Override
    public User findUserByEmail(String email) {
        return usersDAO.findByEmail(email);
    }

    @Override
    public void save(WebUser webUser) {
        User user = new User();

        // Assign user details to the user object
        user.setUserName(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setNric(webUser.getNric());
        user.setEmail(webUser.getEmail());
        user.setEnabled(false);

        // Give user default role of "ROLE_AGENTS"
        user.setRoles(Arrays.asList(rolesDAO.findRoleByName("ROLE_AGENTS")));

        // Save user in the database
        usersDAO.save(user);
    }

    @Override
    public User findById(Long userId) {
        return usersDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return usersDAO.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        usersDAO.deleteById(userId);
    }

    @Override
    public void update(WebUser webUser, Long userId) {
        User existingUser = usersDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserName(webUser.getUserName());

        if (webUser.getPassword() != null && !webUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(webUser.getPassword()));
        }

        existingUser.setFirstName(webUser.getFirstName());
        existingUser.setLastName(webUser.getLastName());
        existingUser.setNric(webUser.getNric());
        existingUser.setEmail(webUser.getEmail());

        usersDAO.save(existingUser);
    }

    @Override
    public void approved(Long userId) {
        Optional<User> user = usersDAO.findById(userId);
        User approve = user.get();
        approve.setEnabled(true);
        usersDAO.save(approve);
    }

    @Override
    public void disapprove(Long userId) {
        Optional<User> user = usersDAO.findById(userId);
        User disapprove = user.get();
        disapprove.setEnabled(false);
        usersDAO.save(disapprove);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usersDAO.findUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return authorities;
    }
}
