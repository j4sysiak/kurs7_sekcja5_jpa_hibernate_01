package pl.jaceksysiak.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import pl.jaceksysiak.hibernate.demo.entity.Course;
import pl.jaceksysiak.hibernate.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;

	@Test
	public void myFirstTest_FindById() {
		System.out.println("Testing is running ...");
		Course course = repository.findById(10001L);
		assertEquals("JPA in 501 Steps", course.getName());
		System.out.println("Testing finish.");
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {

		// get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 501 Steps", course.getName());

		// update details
		course.setName("JPA in 501 Steps - Updated");

		repository.save(course);

		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 501 Steps - Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}