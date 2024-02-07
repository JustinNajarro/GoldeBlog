package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.model.Role;
import edu.idat.pe.goldenBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<edu.idat.pe.goldenBlog.model.User> userOptional = userRepository.findByEmailUser(username);

        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no existe en el sistema!");
        }

        edu.idat.pe.goldenBlog.model.User user = userOptional.orElseThrow();

        Role userRole = user.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(userRole.getAuthority()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmailUser(),
                user.getPasswordUser(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
