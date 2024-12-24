INSERT INTO Course (course_id, course_name) VALUES
(1, 'B.Tech'),
(2, 'MCA'),
(3, 'BBA');

INSERT INTO Student (student_id, student_name, student_age) VALUES
(1, 'Alice Johnson', 25),
(2, 'Bob Smith', 20),
(3, 'Carol Davis', 30),
(4, 'David Wilson', 40),
(5, 'Eve Brown', 25),
(6, 'Frank Miller', 28),
(7, 'Grace Taylor', 26),
(8, 'Henry And', 22),
(9, 'Ivy Thomas', 23),
(10, 'Jack White', 21);


-- Insert Student-Course Relationships
INSERT INTO Student_Course (student_id, course_id) 
VALUES
(1, 1), (1, 2),  -- Alice Johnson is enrolled in B.Tech and MCA
(2, 1), (2, 3),  -- Bob Smith is enrolled in B.Tech and BBA
(3, 2),          -- Carol Davis is enrolled in MCA
(4, 3),          -- David Wilson is enrolled in BBA
(5, 1),          -- Eve Brown is enrolled in B.Tech
(6, 2),          -- Frank Miller is enrolled in MCA
(7, 3), (7, 2),  -- Grace Taylor is enrolled in BBA and MCA
(8, 1),          -- Henry And is enrolled in B.Tech
(9, 3), (9, 2),  -- Ivy Thomas is enrolled in BBA and MCA
(10, 1), (10, 3);-- Jack White is enrolled in B.Tech and BBA