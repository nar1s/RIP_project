INSERT INTO taskly_user(user_id, user_login, user_email, user_password, registration_date)
VALUES (1, 'admin', 'admin@admin.ru', 'admin', '2024-12-06'),
       (2, 'user','user@user.ru', 'user', '2024-12-06');

INSERT INTO taskly_deal(deal_id, user_id, deal_name, deal_description, deal_status, deal_date_start, deal_date_end)
VALUES (1, 1, 'Добавить пользователя', 'Добавить пользователя с именем user2', 'IN_PROCESS', '2024-12-06 00:00:00','2024-12-26 00:00:00'),
       (2, 2, 'Сделать задание', 'Сделать расчетное задание по математическому анализу', 'IN_PROCESS', '2024-12-10 00:00:00', '2024-12-20 00:00:00'),
       (3, 1, 'Сделать задание', 'Сделать расчетное задание', 'IN_PROCESS', '2024-12-07 00:00:00', '2024-12-24 00:00:00'),
       (4, 1, 'Сделать уборку', 'Убраться во всех комнатах','IN_PROCESS', '2024-12-07 00:00:00', '2024-12-23 00:00:00');