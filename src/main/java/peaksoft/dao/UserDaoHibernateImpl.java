package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao, AutoCloseable {
    public final EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        System.out.println("Successfully created users table!");
        entityManager.close();
        

    }

    @Override
    public void dropUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("drop table users").executeUpdate();
        entityManager.getTransaction().commit();
        System.out.println("Successfully dropped users table!");
        entityManager.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        System.out.println("Successfully added new user to users table!");
        entityManager.close();

    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class,id));
        entityManager.getTransaction().commit();
        System.out.println("You've successfully removed user from users table");
        entityManager.close();

    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table users").executeUpdate();
        entityManager.getTransaction().commit();
        System.out.println("Successfully cleaned users table!");
        entityManager.close();

    }

    @Override
    public boolean existsByFirstName(String firstName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select case when count(u) > 0 " +
                "then true " +
                "else false end " +
                "from User u where u.name = ?1", Boolean.class);
        query.setParameter(1, firstName);
        Boolean singleResult = (Boolean) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return singleResult;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
