package kz.epam.intlab.dao;

import kz.epam.intlab.entity.User;
import kz.epam.intlab.util.SessionFactoryBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserDao {

    @EJB
    private SessionFactoryBean sessionFactoryBean;

    private Session session;

    public User getUserByEmail(String email) throws DaoException {
        try {
            session = sessionFactoryBean.getSessionFactory().openSession();
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
            session = sessionFactoryBean.getSessionFactory().openSession();
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
