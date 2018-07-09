package kz.epam.intlab.service;

import kz.epam.intlab.dao.DaoException;
import kz.epam.intlab.dao.UserDao;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

//@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl  {

//    private UserDao userDao;
//    @Inject
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        kz.epam.intlab.entity.User user = null;
//        List<GrantedAuthority> listOfAuthority = new ArrayList<>();
//        try {
//            user = userDao.getUserByEmail(email);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        listOfAuthority.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//        return new User(user.getEmail(), user.getPassword(), true, true, true, true, listOfAuthority);
//    }
}
