-- roles

INSERT INTO roles (id, name)
VALUES (1, 'USER');
INSERT INTO roles (id, name)
VALUES (2, 'ADMIN');

-- some test users

INSERT INTO users (id, description, email, password, username)
VALUES (1, 'first', 'admin@admin.com', 'ffc332a39508c83f89c1c398d51694a93dc3684b6747b3ec45943c459e61ff55193da34dc90905d481bbf73b6bf2db83', 'gosho');

INSERT INTO users (id, description, email, password, username)
VALUES (2, 'second', 'user@user.com', 'ffc332a39508c83f89c1c398d51694a93dc3684b6747b3ec45943c459e61ff55193da34dc90905d481bbf73b6bf2db83', 'pesho');

INSERT INTO users (id, description, email, password, username)
VALUES (3, 'third', 'user@mail.abc', 'ffc332a39508c83f89c1c398d51694a93dc3684b6747b3ec45943c459e61ff55193da34dc90905d481bbf73b6bf2db83', 'stancho');

INSERT INTO users (id, description, email, password, username)
VALUES (4, 'fourth', 'admin@firemail.com', 'ffc332a39508c83f89c1c398d51694a93dc3684b6747b3ec45943c459e61ff55193da34dc90905d481bbf73b6bf2db83', 'vasko');

-- user roles

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (2, 1);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (3, 1);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (4, 2);


INSERT INTO `tags` (id, name)
VALUES (1, 'Sport');
INSERT INTO `tags` (id, name)
VALUES (2, 'Movies');
INSERT INTO `tags` (id, name)
VALUES (3, 'Music');
INSERT INTO `tags` (id, name)
VALUES (4, 'DIY');
INSERT INTO `tags` (id, name)
VALUES (5, 'Review');
INSERT INTO `tags` (id, name)
VALUES (6, 'Gaming');
