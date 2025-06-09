package com.lcdw.orm.learn_spring_orm;

import com.lcdw.orm.learn_spring_orm.couple.Animal;
import com.lcdw.orm.learn_spring_orm.couple.Cat;
import com.lcdw.orm.learn_spring_orm.couple.Dog;
import com.lcdw.orm.learn_spring_orm.couple.Person;
import com.lcdw.orm.learn_spring_orm.entities.Laptop;
import com.lcdw.orm.learn_spring_orm.entities.Student;
import com.lcdw.orm.learn_spring_orm.entities.User;
import com.lcdw.orm.learn_spring_orm.repositories.StudentReposatory;
import com.lcdw.orm.learn_spring_orm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class LearnSpringOrmApplication implements CommandLineRunner {

	private Logger logger= LoggerFactory.getLogger(LearnSpringOrmApplication.class);
	@Autowired
	private UserService userService;
	@Autowired
	private StudentReposatory studentReposatory;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LearnSpringOrmApplication.class, args);
		Person bean = context.getBean(Person.class);
		bean.playWithAnimal();
	}

	@Override
	public void run(String... args) throws Exception {
		/*User user=new User();
		user.setName("Ankit");
		user.setAge(25);
		user.setCity("Bihar");
		User savedUser = userService.saveUser(user);
		System.out.println(savedUser); */

		//Get All User;
		/*List<User> allUser = userService.getAllUser();
		logger.info("User size {}",allUser.size());
		logger.info("Users {}",allUser); */

		//Get Single User
		/*User user = userService.getUser(1);
		logger.info("User {}" ,user); */

		//Update user
//		User user=new User();
//		user.setName("Akhilesh Prasad");
//		user.setAge(50);
//		user.setCity("Delhi cant");
//		User user1 = userService.updateUser(user,1);
//		logger.info("this is the log {}",user1);
		//userService.deleteUser(2);
//		Student student=new Student();
//		student.setStudentName("Anki Prasad");
//		student.setAbout("Me Ankit");
//		student.setStudentId(2);
//		Student save = studentReposatory.save(student);
//		logger.info("Save Student {}",save);
//		Laptop laptop=new Laptop();
//		laptop.setModelNo("123");
//		laptop.setBrand("vu");
//		laptop.setLaptopId(1234);
//		laptop.setStudent(student);
//		student.setLaptop(laptop);
//		Student student1 = studentReposatory.findById(1).get();
//		Animal animal=new Cat();
//		Person p=new Person(animal);
//		p.playWithAnimal();
	}
}
