CREATE TABLE COURSES (
    id UUID PRIMARY KEY,
    description varchar(255) NOT NULL,
    title varchar(200) NOT NULL,
    instructor_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (instructor_id) references users (id)
)