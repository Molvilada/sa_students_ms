package sa.student.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({@NamedQuery(name = Student.FIND_ALL, query = "SELECT u FROM Student u"),
               @NamedQuery( name = Student.FIND_BY_USERNAME, query = "SELECT u FROM Student u WHERE u.username = :username")})

public class Student {

    public static final String FIND_ALL = "Student.findAll";
    public static final String FIND_BY_USERNAME = "Student.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String username;
    private String password;

    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }

    public String getUsername(){
      return username;
    }
    public void setUsername(String username){
      this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
