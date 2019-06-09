package sa.student.service;

import sa.student.model.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Student> getAllStudents(int first, int maxResult) {
        return entityManager.createNamedQuery(Student.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public Student getStudentByCode(long code){
        return entityManager.find(Student.class, code);
    }

    public Student createStudent(Student student) {
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    public Student updateStudent(long code, Student student) {
        Student studentToUpdate = entityManager.find(Student.class, code);
        studentToUpdate.setUsername(student.getUsername());
        studentToUpdate.setPassword(student.getPassword());
        entityManager.merge(studentToUpdate);
        return entityManager.find(Student.class, code);
    }

    public long deleteStudent(long code) {
        Student student = entityManager.find(Student.class, code);
        entityManager.remove(student);
        return code;
    }
}
