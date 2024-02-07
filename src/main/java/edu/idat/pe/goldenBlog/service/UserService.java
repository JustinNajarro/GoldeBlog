package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.exception.UserException;
import edu.idat.pe.goldenBlog.model.User;
import edu.idat.pe.goldenBlog.repository.RoleRepository;
import edu.idat.pe.goldenBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User newUser) {
        if (userIsExist(newUser.getNameUser(), newUser.getLastnameUser())) {
            throw new UserException("Ya existe un usuario con el mismo nombre y apellido.");
        }

        assignUserRole(newUser);
        newUser.setPasswordUser(passwordEncoder.encode(newUser.getPasswordUser()));
        userRepository.save(newUser);
        return newUser;
    }

    public boolean userIsExist(String nameUser, String lastnameUser) {
        Optional<User> userIsPresent = userRepository.findByNameUserAndLastnameUser(nameUser, lastnameUser);
        return userIsPresent.isPresent();
    }

    private void assignUserRole(User user) {
        try {
            user.setRole(roleRepository.findByNameRol("ROLE_USER"));
        } catch (Exception ex) {
            throw new UserException("Error al asignar rol al usuario");
        }
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
    public List<User> getUsersByRole(String roleName) {
        return userRepository.findByRole_NameRol(roleName);
    }

}
