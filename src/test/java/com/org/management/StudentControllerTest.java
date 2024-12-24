package com.org.management;

import com.org.management.controller.StudentController;
import com.org.management.entity.Student;
import com.org.management.service.StudentService;
import com.org.management.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        // Given
        Integer studentId = 1;
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName("Alice Johnson");
        student.setStudentAge(25);

        // When
        when(studentService.getStudentById(studentId)).thenReturn(Optional.of(student));

        // Then
        mockMvc.perform(get("/api/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Alice Johnson"));
    }

    @Test
    public void testGetStudentByIdNotFound() throws Exception {
        // Given
        Integer studentId = 1;

        // When
        when(studentService.getStudentById(studentId)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(get("/api/students/{id}", studentId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateStudent() throws Exception {
        // Given
        Student student = new Student();
        student.setStudentName("Bob Smith");
        student.setStudentAge(20);

        // When
        when(studentService.createStudent(student)).thenReturn(student);

        // Then
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content("{\"studentName\":\"Bob Smith\", \"studentAge\":20}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentName").value("Bob Smith"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Given
        Integer studentId = 1;
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName("Alice Johnson Updated");
        student.setStudentAge(26);

        // When
        when(studentService.getStudentById(studentId)).thenReturn(Optional.of(student));
        when(studentService.updateStudent(eq(studentId), any(Student.class))).thenReturn(student);

        // Then
        mockMvc.perform(put("/api/students/{id}", studentId)
                .contentType("application/json")
                .content("{\"studentName\":\"Alice Johnson Updated\", \"studentAge\":26}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Alice Johnson Updated"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        // Given
        Integer studentId = 1;
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName("Alice Johnson");

        // When
        when(studentService.getStudentById(studentId)).thenReturn(Optional.of(student));
        doNothing().when(studentService).deleteStudent(studentId);

        // Then
        mockMvc.perform(delete("/api/students/{id}", studentId))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    @Test
    public void testDeleteStudentNotFound() throws Exception {
        // Given
        Integer studentId = 1;

        // When
        when(studentService.getStudentById(studentId)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(delete("/api/students/{id}", studentId))
                .andExpect(status().isNotFound());
    }
}
