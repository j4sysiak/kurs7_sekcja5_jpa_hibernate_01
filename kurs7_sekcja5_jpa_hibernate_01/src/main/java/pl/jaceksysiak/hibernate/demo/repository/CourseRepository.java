package pl.jaceksysiak.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.jaceksysiak.hibernate.demo.entity.Course;


@Repository
@Transactional
public class CourseRepository {	
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id){
		return em.find(Course.class, id);
	}
	
	public Course save(Course course) {		
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}	
		return course;
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	//public Course save(Course course) -> insert or update
	
	

}
