CREATE TABLE MODULES (
    ID UUID PRIMARY KEY,
    title varchar(200) NOT NULL,
    description varchar(300) NOT NULL,
    order_number INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    course_id UUID NOT NULL,
    FOREIGN KEY (course_id) REFERENCES COURSES (id)
)