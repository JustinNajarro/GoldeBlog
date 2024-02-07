package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.model.Role;
import edu.idat.pe.goldenBlog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
