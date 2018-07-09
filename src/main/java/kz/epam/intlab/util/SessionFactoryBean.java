package kz.epam.intlab.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class SessionFactoryBean {

    public SessionFactory getSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        System.out.println(registry+" ??????????????????????????????????????????");
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            System.out.println(sessionFactory + " ???????????????????????????????????????????");
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return sessionFactory;
    }
}