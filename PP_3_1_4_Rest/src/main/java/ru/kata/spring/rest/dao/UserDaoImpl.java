package ru.kata.spring.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.rest.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.security.InvalidParameterException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        setEncryptedPassword(user);
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) throws InvalidParameterException {
        if (user.getPassword().isEmpty()) {
            user.setPassword(getUserById(user.getId()).getPassword());
        }
        if (!(user.getPassword().equals(getUserById(user.getId()).getPassword())) &
                !(user.getPassword().isEmpty())) {
            setEncryptedPassword(user);
        }
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT user FROM User user WHERE user.username = :userRequest", User.class);
        return query.setParameter("userRequest", username).getSingleResult();
    }

    public void setEncryptedPassword(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}