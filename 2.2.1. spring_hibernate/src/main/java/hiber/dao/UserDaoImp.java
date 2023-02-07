package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserToAvto(String model, int series) {
        List<User> userList = null;
        try {
            List<Car> cartList = sessionFactory.openSession().createQuery("from Car c fetch all properties where lower(c.model)=: paramModel and lower(c.series)=: paramSeries", Car.class).
                    setParameter("paramModel", model).setParameter("paramSeries", series).getResultList();
            userList = new ArrayList<>();
            for (Car car : cartList) {
                userList.add(car.getUser());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return userList;
    }
}
