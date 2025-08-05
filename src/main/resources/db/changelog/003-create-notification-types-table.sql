CREATE TABLE notification_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO notification_types (name) VALUES
('SMS'),
('E-Mail'),
('Push Notification');