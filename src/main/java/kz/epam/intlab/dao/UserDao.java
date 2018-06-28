package kz.epam.intlab.dao;

import kz.epam.intlab.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public User getUserByEmail(String username) throws DaoException {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            User result = (User) session.createQuery("from User where username=:username").setParameter("username", username).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DaoException();
        }
    }
}
