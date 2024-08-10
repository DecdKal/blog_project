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

-- tags

INSERT INTO `tags` (id, name)
VALUES (1, 'Fun');
INSERT INTO `tags` (id, name)
VALUES (2, 'Daring');
INSERT INTO `tags` (id, name)
VALUES (3, 'Dancing');
INSERT INTO `tags` (id, name)
VALUES (4, 'Blessed');
INSERT INTO `tags` (id, name)
VALUES (5, 'Influencer');
INSERT INTO `tags` (id, name)
VALUES (6, 'Youtube');
INSERT INTO `tags` (id, name)
VALUES (7, 'Streaming');

-- categories

INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('1', 'Sports', 'Sports');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('2', 'Gaming', 'Gaming');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('3', 'Life advice', 'Life Advice');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('4', 'Review', 'Review');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('5', 'DIY', 'DIY');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('6', 'Music', 'Music');
INSERT INTO `blog`.`categories` (`id`, `description`, `name`)
VALUES ('7', 'Movies', 'Movies');
