package pl.jaceksysiak.hibernate.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.jaceksysiak.hibernate.demo.entity.Course;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
 
	
	@Test
	public void Native_Queries_basic() {
		Query query = em.createNativeQuery("select * from course");
		List resultList = query.getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	
	@Test
	public void Native_Queries_parameter() {
		Query query = em.createNativeQuery("select * from course where id = ?");
		query.setParameter(1, 10000L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = ? -> {}", resultList);
	}
	
	@Test
	public void Native_Queries_named_parameter() {
		Query query = em.createNativeQuery("select * from course where id = :id");
		query.setParameter("id", 10000L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = :id -> {}", resultList);
	}
	


}
