package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements  CarDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("FROM Car");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getCarsUser(String licensePlate, String series) {
        String hql = "SELECT u FROM Car c INNER JOIN User u ON (u.id=c.id) WHERE c.licensePlate=:l AND c.series=:s";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("l",licensePlate);
        query.setParameter("s",series);
        return query.getSingleResult();
    }
}
