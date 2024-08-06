-- roles

INSERT INTO roles (id, name)
VALUES (1, 'USER');
INSERT INTO roles (id, name)
VALUES (2, 'ADMIN');

-- some test users

INSERT INTO users (id, description, email, password, username)
VALUES (1, 'first', 'admin@admin.com', '1234', 'gosho');

INSERT INTO users (id, description, email, password, username)
VALUES (2, 'second', 'user@user.com', '1234', 'pesho');

INSERT INTO users (id, description, email, password, username)
VALUES (3, 'third', 'user@mail.abc', '1234', 'stancho');

INSERT INTO users (id, description, email, password, username)
VALUES (4, 'fourth', 'admin@firemail.com', '1234', 'vasko');

-- user roles

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (2, 2);


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
