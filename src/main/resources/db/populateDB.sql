DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('User2', 'user2@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2016-12-22 09:00:00', 'Завтрак', 2000),
  (100000, '2016-12-23 12:00:00', 'Обед', 500),
  (100000, '2016-12-23 18:00:00', 'Ужин', 100),
  (100000, '2016-12-22 18:00:00', 'Ужин', 1000),
  (100000, '2016-12-22 12:00:00', 'Обед', 500),

  (100002, '2016-12-23 18:00:00', 'Ужин', 1000),
  (100002, '2016-12-22 09:00:00', 'Завтрак', 2000),
  (100002, '2016-12-23 09:00:00', 'Завтрак', 1000);
