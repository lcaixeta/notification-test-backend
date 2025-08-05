CREATE TABLE log_history (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    notification_type_id INT NOT NULL,
    message TEXT NOT NULL,
    sent_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_log_user FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT fk_log_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_log_type FOREIGN KEY (notification_type_id) REFERENCES notification_types(id)
);

INSERT INTO log_history (user_id, category_id, notification_type_id, message, sent_timestamp) VALUES
    ((SELECT id FROM "user" WHERE email = 'alice@example.com'),
     (SELECT id FROM categories WHERE name = 'Movies'),
     (SELECT id FROM notification_types WHERE name = 'SMS'),
     'Hi Alice! Check out the new movies of the week!',
     '2025-08-03 10:30:00'),

    ((SELECT id FROM "user" WHERE email = 'alice@example.com'),
     (SELECT id FROM categories WHERE name = 'Finance'),
     (SELECT id FROM notification_types WHERE name = 'E-Mail'),
     'Alice, here are your weekly financial tips.',
     '2025-08-03 12:00:00'),

    ((SELECT id FROM "user" WHERE email = 'bruno@example.com'),
     (SELECT id FROM categories WHERE name = 'Sports'),
     (SELECT id FROM notification_types WHERE name = 'Push Notification'),
     'Bruno, your favorite team plays today at 8 PM!',
     '2025-08-03 13:45:00'),

    ((SELECT id FROM "user" WHERE email = 'caio@example.com'),
     (SELECT id FROM categories WHERE name = 'Finance'),
     (SELECT id FROM notification_types WHERE name = 'SMS'),
     'Caio, new investment opportunities are available.',
     '2025-08-03 14:10:00'),

    ((SELECT id FROM "user" WHERE email = 'caio@example.com'),
     (SELECT id FROM categories WHERE name = 'Movies'),
     (SELECT id FROM notification_types WHERE name = 'E-Mail'),
     'Don’t miss this weekend’s movie premieres!',
     '2025-08-03 15:00:00'),

    ((SELECT id FROM "user" WHERE email = 'caio@example.com'),
     (SELECT id FROM categories WHERE name = 'Sports'),
     (SELECT id FROM notification_types WHERE name = 'Push Notification'),
     'Live now: your favorite sports event!',
     '2025-08-03 16:30:00'),

    ((SELECT id FROM "user" WHERE email = 'filipe@example.com'),
     (SELECT id FROM categories WHERE name = 'Finance'),
     (SELECT id FROM notification_types WHERE name = 'SMS'),
     'Filipe, your account summary is ready.',
     '2025-08-03 17:15:00');
