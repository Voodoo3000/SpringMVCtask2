package kz.epam.intlab.dao;

import kz.epam.intlab.entity.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NewsDao implements Dao {

    //private static final Logger LOGGER = Logger.getLogger(NewsDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public Map<Integer, News> getAllNews() {
        Map<Integer, News> newsMap = new HashMap<>();
        try {
            session = sessionFactory.openSession();
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
            session = sessionFactory.openSession();
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
    public void addUpdateNews(News news) throws DaoException {
        try {
        session = sessionFactory.openSession();
        session.beginTransaction();
        if (news.getId() != 0) {
            session.update(news);
        } else session.save(news);
        session.getTransaction().commit();
        session.close();
        } catch (HibernateException e) {
            //LOGGER.error("HibernateException in addUpdateNews", e);
            throw new DaoException();
        }
    }

    @Override
    public void deleteNews(News news) throws DaoException {
        try {
        session = sessionFactory.openSession();
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
