-- roles

INSERT INTO roles (id, name)
VALUES (1, 'USER');
INSERT INTO roles (id, name)
VALUES (2, 'ADMIN');

-- some test users

INSERT INTO users (id, description, email, password, username, uuid)
VALUES (1, 'first', 'admin@admin.com', 'ffc332a39508c83f89c1c398d51694a93dc3684b6747b3ec45943c459e61ff55193da34dc90905d481bbf73b6bf2db83', 'gosho', '8a7716f9-4eab-40e0-b854-2591f095c7ad');

-- user roles

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 2);

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
