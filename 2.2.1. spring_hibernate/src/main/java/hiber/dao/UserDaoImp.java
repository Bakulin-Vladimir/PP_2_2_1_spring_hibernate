package hiber.dao;

import hiber.MainApp;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

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

    public List<User> getUserToAvto(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car where model=:paramModel and series=:paramSeries");
        List<Car> cartList = query.setParameter("paramModel", model).setParameter("paramSeries", series).getResultList();
        List<User> userList = new ArrayList<>();
        for (Car car : cartList) {
            userList.add(car.getUser());
        }
        return userList;
    }
}
