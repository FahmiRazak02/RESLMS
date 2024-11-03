package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolesDAOImpl implements RolesDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String roleName) {

        // Retrieve/read from database using name
        TypedQuery<Role> query = entityManager.createQuery("from Role where role=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        Role role = null;

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            role = null;
        }

        return role;
    }
}
