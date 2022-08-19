INSERT INTO USERS(name, email, password) VALUES('Student', 'student@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USERS(name, email, password) VALUES('Moderator', 'moderator@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO PROFILES(id, name) VALUES(1, 'ROLE_STUDENT');
INSERT INTO PROFILES(id, name) VALUES(2, 'ROLE_MODERATOR');

INSERT INTO USERS_PROFILES(user_id, profiles_id) VALUES(1, 1);
INSERT INTO USERS_PROFILES(user_id, profiles_id) VALUES(2, 2);

INSERT INTO COURSES(nome, category) VALUES('Spring Boot', 'Programming');
INSERT INTO COURSES(nome, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICS(title, message, creation_date, status, author_id, course_id) VALUES('Question', 'Error when creating a project', '2019-05-05 18:00:00', 'UNANSWERED', 1, 1);
INSERT INTO TOPICS(title, message, creation_date, status, author_id, course_id) VALUES('Question 2', 'Project not compiling', '2019-05-05 19:00:00', 'UNANSWERED', 1, 1);
INSERT INTO TOPICS(title, message, creation_date, status, author_id, course_id) VALUES('Question 3', 'HTML tag', '2019-05-05 20:00:00', 'UNANSWERED', 1, 2);
