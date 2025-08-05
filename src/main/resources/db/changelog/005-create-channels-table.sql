CREATE TABLE channels (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    notification_type_id INT NOT NULL,
    CONSTRAINT fk_channels_user FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT fk_channels_notification_type FOREIGN KEY (notification_type_id) REFERENCES notification_types(id)
);

INSERT INTO channels (user_id, notification_type_id) VALUES
    ((SELECT id FROM "user" WHERE email = 'alice@example.com'), (SELECT id FROM notification_types WHERE name = 'SMS')),
    ((SELECT id FROM "user" WHERE email = 'alice@example.com'), (SELECT id FROM notification_types WHERE name = 'E-Mail')),
    ((SELECT id FROM "user" WHERE email = 'bruno@example.com'), (SELECT id FROM notification_types WHERE name = 'Push Notification')),
    ((SELECT id FROM "user" WHERE email = 'caio@example.com'), (SELECT id FROM notification_types WHERE name = 'SMS')),
    ((SELECT id FROM "user" WHERE email = 'caio@example.com'), (SELECT id FROM notification_types WHERE name = 'E-Mail')),
    ((SELECT id FROM "user" WHERE email = 'caio@example.com'), (SELECT id FROM notification_types WHERE name = 'Push Notification')),
    ((SELECT id FROM "user" WHERE email = 'debora@example.com'), (SELECT id FROM notification_types WHERE name = 'E-Mail')),
    ((SELECT id FROM "user" WHERE email = 'filipe@example.com'), (SELECT id FROM notification_types WHERE name = 'SMS')),
    ((SELECT id FROM "user" WHERE email = 'lucas@example.com'), (SELECT id FROM notification_types WHERE name = 'SMS')),
    ((SELECT id FROM "user" WHERE email = 'lucas@example.com'), (SELECT id FROM notification_types WHERE name = 'E-Mail')),
    ((SELECT id FROM "user" WHERE email = 'roseli@example.com'), (SELECT id FROM notification_types WHERE name = 'Push Notification'));
