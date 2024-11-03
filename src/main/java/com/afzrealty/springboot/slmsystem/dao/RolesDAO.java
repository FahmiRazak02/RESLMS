package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.Role;

public interface RolesDAO {

    Role findRoleByName(String roleName);
}
