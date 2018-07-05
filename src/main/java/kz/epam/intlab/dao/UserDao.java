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

    public User getUserByEmail(String email) throws DaoException {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            User result = (User) session.createQuery("from User where email=:email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DaoException();
        }
    }

    public void addUpdateUser(User user) throws DaoException {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            if (user.getId() != 0) {
                session.update(user);
            } else session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new DaoException();
        }
    }
}
