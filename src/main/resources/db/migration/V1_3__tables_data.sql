INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (2, 'Admin', 'Admin', '2023-02-16 16:57:16.000000', 'Admin_Admin_2', 80, 'admin.admin.2@noirix.com', 'admin_admin_2', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (4, '1', '1', '2023-02-16 16:57:54.000000', '1_1_4', 80, '1.1.4@noirix.com', '1_1_4', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (23, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_23', 80, 'slava.kalevich.23@noirix.com', 'slava_kalevich_23', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (24, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_24', 80, 'slava.kalevich.24@noirix.com', 'slava_kalevich_24', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (25, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_25', 80, 'slava.kalevich.25@noirix.com', 'slava_kalevich_25', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (26, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_26', 80, 'slava.kalevich.26@noirix.com', 'slava_kalevich_26', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (27, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_27', 80, 'slava.kalevich.27@noirix.com', 'slava_kalevich_27', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (28, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_28', 80, 'slava.kalevich.28@noirix.com', 'slava_kalevich_28', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (29, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_29', 80, 'slava.kalevich.29@noirix.com', 'slava_kalevich_29', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (30, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_30', 80, 'slava.kalevich.30@noirix.com', 'slava_kalevich_30', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (31, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_31', 80, 'slava.kalevich.31@noirix.com', 'slava_kalevich_31', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (32, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_32', 80, 'slava.kalevich.32@noirix.com', 'slava_kalevich_32', 'NOT_SELECTED');
INSERT INTO public.users (id, name, surname, birth_date, full_name, weight, email, user_password, gender) VALUES (1, 'Slava', 'Kalevich', '1996-07-08 16:56:27.000000', 'Slava_Kalevich_1', 80, 'slava.kalevich.1@noirix.com', 'slava_kalevich_1', 'NOT_SELECTED');

INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (1, 'ROLE_ADMIN', 2, '2023-04-04 19:12:28.000000', '2023-04-04 19:12:29.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (3, 'ROLE_USER', 2, '2023-04-04 19:13:04.000000', '2023-04-04 19:13:05.000000');


INSERT INTO public.locations (id, country, city, created, changed, visible) VALUES (1, 'Poland', 'Warsaw', '2023-02-16 19:24:38.000000', '2023-02-16 19:24:40.000000', true);
INSERT INTO public.locations (id, country, city, created, changed, visible) VALUES (2, 'Poland', 'Lodz', '2023-02-16 19:24:38.000000', '2023-02-16 19:24:40.000000', true);
INSERT INTO public.locations (id, country, city, created, changed, visible) VALUES (3, 'Poland', 'Wroclaw', '2023-02-16 19:24:38.000000', '2023-02-16 19:24:40.000000', true);
INSERT INTO public.locations (id, country, city, created, changed, visible) VALUES (4, 'Poland', 'Gdansk', '2023-02-16 19:24:38.000000', '2023-02-16 19:24:40.000000', true);

INSERT INTO public.l_users_locations (id, user_id, location_id, created, changed) VALUES (1, 1, 1, '2023-02-16 19:25:16.000000', '2023-02-16 19:25:18.000000');
INSERT INTO public.l_users_locations (id, user_id, location_id, created, changed) VALUES (2, 2, 2, '2023-02-16 19:25:16.000000', '2023-02-16 19:25:18.000000');
INSERT INTO public.l_users_locations (id, user_id, location_id, created, changed) VALUES (3, 27, 4, '2023-02-16 19:25:16.000000', '2023-02-16 19:25:18.000000');


INSERT INTO public.cars (id, name, brand, price, user_id, created, changed, is_deleted) VALUES (1, 'RS7', 'Audi', 120000, 1, '2023-02-16 17:33:52.000000', '2023-02-16 17:33:54.000000', false);
INSERT INTO public.cars (id, name, brand, price, user_id, created, changed, is_deleted) VALUES (3, 'RS7', 'Audi', 120000, 2, '2023-02-16 17:33:52.000000', '2023-02-16 17:33:54.000000', false);
INSERT INTO public.cars (id, name, brand, price, user_id, created, changed, is_deleted) VALUES (4, 'RSQ8', 'Audi', 120000, null, '2023-02-16 17:33:52.000000', '2023-02-16 17:33:54.000000', false);
