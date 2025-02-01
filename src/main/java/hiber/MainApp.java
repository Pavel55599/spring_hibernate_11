
package hiber;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.ArrayList;




public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      List<User> users = new ArrayList<>();
      users.add(new User("ПАША", "ВОЛЯ", "ПАВЛИК@mail.com"));
      users.add(new User("ИВАН", "ВЕТРОВ", "ИВАН@mail.com"));
      users.add(new User("ФЕДОР", "ДОСТОЕВСКИЙ", "ФЕДЯ@mail.com"));
      users.add(new User("АЛЕКСАНДР", "ПУШКИН", "САНЯ@mail.com"));


      System.out.println("Сохранение пользователей в БД");
      for (User user : users) {
         userService.add(user);
      }

      List<Car> cars = new ArrayList<>();
      cars.add(new Car("жигули", 1985));
      cars.add(new Car("МАЗДА", 3));
      cars.add(new Car("МЕРСЕДЕС", 600));
      cars.add(new Car("ВОЛГА", 21));


      System.out.println("Сохранение машин в БД");
      for (Car car : cars) {
         carService.add(car);
      }

      System.out.println("получение списка пользователей");
      List<User> savedUsers = userService.listUsers();
      List<Car> savedCars = carService.listCars();

      System.out.println("список юзеров " + savedUsers);
      System.out.println("список машин " + savedCars);

      System.out.println("раздача машин");
      assignCarsToUsers(savedUsers, savedCars);

      System.out.println("Сохранение обновленных пользователей в БД");
      for (User user : savedUsers) {
         userService.updateUser(user);
      }

      for (User user : savedUsers) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
         System.out.println("_____________________________");
      }

      for (Car car : savedCars) {
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("жигули", 1985));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("МАЗДА", 3));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("МЕРСЕДЕС", 600));
      System.out.println("-----------------------------------");
      System.out.println("ВЫВОЖУ ПОЛЬЗОВАТЕЛЯ ПО АВТОМОБИЛЮ :" + userService.getUserByCarModelAndSeries("ВОЛГА", 21));
      System.out.println("-----------------------------------");

      context.close();
   }

   public static void assignCarsToUsers(List<User> users, List<Car> cars) {
      for (int i = 0; i < users.size(); i++) {
         if (i < cars.size()) {
            users.get(i).setCar(cars.get(i));
         } else {
            users.get(i).setCar(null);
         }
      }
   }
}


//DELETE FROM cars;
//SET SQL_SAFE_UPDATES = 0;





