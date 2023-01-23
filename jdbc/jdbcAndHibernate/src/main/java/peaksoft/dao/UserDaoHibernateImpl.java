package peaksoft.dao;

import config.HibernateConfig;
import org.hibernate.Session;
import peaksoft.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        HibernateConfig.getSession();
        System.out.println("Table users created.");
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateConfig.getSession().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table users deleted.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateConfig.getSession().openSession();
        session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User saved.");
    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateConfig.getSession().openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User deleted.");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateConfig.getSession().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateConfig.getSession().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table users cleaned.");
    }
}
