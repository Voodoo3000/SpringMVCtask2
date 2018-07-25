package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Resource(lookup = "java:app/SpringMVCTask2/ServiceImpl")
    private kz.epam.intlab.service.Service service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        kz.epam.intlab.dto.UserDTO userDTO = null;
        List<GrantedAuthority> listOfAuthority = new ArrayList<>();
        try {
            userDTO = service.getUserByEmail(email);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        listOfAuthority.add(new SimpleGrantedAuthority("ROLE_" + userDTO.getRole()));
        return new User(userDTO.getEmail(), userDTO.getPassword(), true, true, true, true, listOfAuthority);
    }
}