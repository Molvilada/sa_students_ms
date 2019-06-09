package sa.student.service;

import sa.student.model.Student;
import sa.student.service.LdapService;
import sa.student.service.StudentService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AuthService {

    @PersistenceContext
    EntityManager entityManager;

    String response;
    LdapService ldapService = new LdapService();
    StudentService studentService = new StudentService();

    public String login(Student student) {

        String username = student.getUsername();
        String password = student.getPassword();

        if (ldapService.connect()) {
            if (ldapService.validateUser(username, password)) {
                List<Student> students = entityManager.createNamedQuery(Student.FIND_BY_USERNAME)
                      .setParameter("username", username).getResultList();
                if (!students.isEmpty()) {
                    for (Student s: students) {
                        if (s.getPassword().equals(password)) {
                            response = "¡Autenticación satisfactoria!";
                        } else {
                            response = "¡Contraseña inválida!";
                        }
                    }
                } else {
                    response = "El usuario no existe en la base de datos...";
                }
            } else {
                response = "El usuario no existe en el servidor LDAP...";
            }
        } else {
            response = "Error de conexión con el servidor LDAP...";
        }
        return response;
    }
}
