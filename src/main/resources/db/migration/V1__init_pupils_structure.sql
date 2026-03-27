-- ============================================================
-- 1. DROP existing schema (if exists)
-- ============================================================
DROP SCHEMA IF EXISTS PUPIL_COURSE_SCHEMA CASCADE;

-- ============================================================
-- 2. Create schema
-- ============================================================
CREATE SCHEMA PUPIL_COURSE_SCHEMA;

SET search_path TO PUPIL_COURSE_SCHEMA;

-- ============================================================
-- 3. Create ENUM type for course status
-- ============================================================
CREATE TYPE COURSE_STATUS AS ENUM (
    'NOT_STARTED',
    'IN_PROGRESS',
    'COMPLETED'
);

-- ============================================================
-- 4. Create tables
-- ============================================================

-- COURSE table
CREATE TABLE COURSE (
    ID SERIAL PRIMARY KEY,
    COURSE_NAME VARCHAR(50) NOT NULL
);

-- PUPIL table
CREATE TABLE PUPIL (
    ID SERIAL PRIMARY KEY,
    PUPIL_FIRSTNAME VARCHAR(100) NOT NULL,
    PUPIL_LASTNAME VARCHAR(100) NOT NULL
);

-- Relationship table: PUPIL_COURSE
CREATE TABLE PUPIL_COURSE (
    ID_PUPIL_COURSE SERIAL PRIMARY KEY,
    ID_PUPIL INTEGER NOT NULL REFERENCES PUPIL(ID),
    ID_COURSE INTEGER NOT NULL REFERENCES COURSE(ID),
    STATUS COURSE_STATUS NOT NULL
);