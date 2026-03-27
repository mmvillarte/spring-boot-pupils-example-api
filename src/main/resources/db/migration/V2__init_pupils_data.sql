-- ============================================================
-- 5. Inserts for testing
-- ============================================================

-- Insert courses
INSERT INTO PUPIL_COURSE_SCHEMA.COURSE (COURSE_NAME)
VALUES
    ('Java Fundamentals'),
    ('Spring Boot Essentials'),
    ('PostgreSQL Basics');

-- Insert pupils
INSERT INTO PUPIL_COURSE_SCHEMA.PUPIL (PUPIL_FIRSTNAME, PUPIL_LASTNAME)
VALUES
    ('John', 'Doe'),
    ('Alice', 'Smith');

-- Insert pupil-course relations
INSERT INTO PUPIL_COURSE_SCHEMA.PUPIL_COURSE (ID_PUPIL, ID_COURSE, STATUS)
VALUES
    (1, 1, 'IN_PROGRESS'),
    (1, 2, 'COMPLETED'),
    (2, 3, 'NOT_STARTED');

-- Insert pupils
INSERT INTO PUPIL_COURSE_SCHEMA.PUPIL (PUPIL_FIRSTNAME, PUPIL_LASTNAME)
VALUES ('Jane', 'Johnson');

-- Insert pupil-course relations
INSERT INTO PUPIL_COURSE_SCHEMA.PUPIL_COURSE (ID_PUPIL, ID_COURSE, STATUS)
VALUES ((SELECT ID FROM PUPIL_COURSE_SCHEMA.PUPIL WHERE PUPIL_FIRSTNAME = 'Jane' AND PUPIL_LASTNAME = 'Johnson'),
        (SELECT ID FROM PUPIL_COURSE_SCHEMA.COURSE WHERE COURSE_NAME = 'Java Fundamentals'),
        'NOT_STARTED');

