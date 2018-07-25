package kz.epam.intlab.dao;

import kz.epam.intlab.entity.News;
import kz.epam.intlab.util.SessionFactoryBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Local(Dao.class)
public class NewsDao implements Dao {

    @EJB
    private SessionFactoryBean sessionFactoryBean;

    private Session session;

    @Override
    public Map<Integer, News> getAllNews() {
        Map<Integer, News> newsMap = new HashMap<>();
        try {
            session = sessionFactoryBean.getSessionFactory().openSession();
            session.beginTransaction();
            List result = session.createQuery("from News").list();
            for (News news : (List<News>) result) {
                newsMap.put(news.getId(), news);
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            //LOGGER.error("HibernateException in getAllNews", e);

        }
        return newsMap;
    }

    @Override
    public News getNewsById(Integer id) throws DaoException {
        try {
            session = sessionFactoryBean.getSessionFactory().openSession();
            session.beginTransaction();
            News result = (News) session.createQuery("from News where id=:id").setParameter("id", id).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            //LOGGER.error("HibernateException in getNewsById", e);
            throw new DaoException();
        }
    }

    @Override
    public News addUpdateNews(News news) throws DaoException {
        try {
        session = sessionFactoryBean.getSessionFactory().openSession();
        session.beginTransaction();
        if (news.getId() != 0) {
            session.update(news);
        } else session.save(news);
        session.getTransaction().commit();
        session.close();
        } catch (HibernateException e) {
            //LOGGER.error("HibernateException in AddUpdateNews", e);
            throw new DaoException();
        }
        return news;
    }

    @Override
    public void deleteNews(News news) throws DaoException {
        try {
        session = sessionFactoryBean.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(news);
        session.getTransaction().commit();
        session.close();
        } catch (HibernateException e) {
            //LOGGER.error("HibernateException in deleteNews", e);
            throw new DaoException();
        }
    }
}
