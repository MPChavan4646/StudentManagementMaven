CREATE TABLE Course (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100)
);

CREATE TABLE Student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    student_age INT
);
CREATE TABLE Student_Course (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (course_id) REFERENCES Course(course_id)
);