package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }



   @Transactional
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery(
                      "from User u where u.car.model = :model and u.car.series = :series"
              ).setParameter("model", model)
              .setParameter("series", series)
              .setMaxResults(1);

      try {
         return (User) query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   @Transactional
   public void updateUser(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.update(user);
   }
}
