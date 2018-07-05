package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        kz.epam.intlab.entity.User user = null;
        List<GrantedAuthority> listOfAuthority = new ArrayList<>();
        try {
            user = dao.getUserByEmail(email);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        listOfAuthority.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, listOfAuthority);
    }
}
