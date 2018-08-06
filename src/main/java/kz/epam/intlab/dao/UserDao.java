package kz.epam.intlab.dao;

import kz.epam.intlab.entity.User;
import kz.epam.intlab.util.SessionFactoryBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class UserDao {

    @EJB
    private SessionFactoryBean sessionFactoryBean;

    private Session session;

    public User getUserByEmail(String email) throws DaoException {
            session = sessionFactoryBean.getSessionFactory().openSession();
            session.beginTransaction();
            User result = (User) session.createQuery("from User where email=:email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
    }

    public User getUserById(int id) throws DaoException {
            session = sessionFactoryBean.getSessionFactory().openSession();
            session.beginTransaction();
            User result = (User) session.createQuery("from User where id=:id").setParameter("id", id).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
    }

    public void addUpdateUser(User user) throws DaoException {
            session = sessionFactoryBean.getSessionFactory().openSession();
            session.beginTransaction();
            if (user.getId() != 0) {
                session.update(user);
            } else session.save(user);
            session.getTransaction().commit();
            session.close();
    }
}
