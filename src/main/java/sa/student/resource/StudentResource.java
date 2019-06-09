package sa.student.resource;

import sa.student.model.Student;
import sa.student.service.StudentService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.persistence.Entity;
import java.net.URI;
import java.util.List;

@Path("/students")
public class StudentResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    StudentService studentService;

    @GET
    public List<Student> getAllStudents(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return studentService.getAllStudents(first, maxResult);
    }

    @GET
    @Path("{code}")
    public Response getStudentByCode(@PathParam("code") long code) {
        Student student = studentService.getStudentByCode(code);
        response = Response.status(Response.Status.OK);
        response.entity(student);
        return response.build();
    }

    @POST
    public Response createStudent(Student student) {
        Student createdStudent = studentService.createStudent(student);
        response = Response.status(Response.Status.CREATED);
        response.entity(createdStudent);
        return response.build();
    }

    @PUT
    @Path("{code}")
    public Response updateStudent(@PathParam("code") long code, Student student) {
        Student updatedStudent = studentService.updateStudent(code, student);
        response = Response.status(Response.Status.OK);
        response.entity(updatedStudent);
        return response.build();
    }

    @DELETE
    @Path("{code}")
    public Response deleteStudent(@PathParam("code") long code) {
        long deletedStudentCode = studentService.deleteStudent(code);
        response = Response.status(Response.Status.OK);
        response.entity(deletedStudentCode);
        return response.build();
    }

}
