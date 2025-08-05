CREATE TABLE subscription (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_subscription_user FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT fk_subscription_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO subscription (user_id, category_id) VALUES
    ((SELECT id FROM "user" WHERE email = 'alice@example.com'), (SELECT id FROM categories WHERE name = 'Sports')),
    ((SELECT id FROM "user" WHERE email = 'alice@example.com'), (SELECT id FROM categories WHERE name = 'Finance')),
    ((SELECT id FROM "user" WHERE email = 'bruno@example.com'), (SELECT id FROM categories WHERE name = 'Movies')),
    ((SELECT id FROM "user" WHERE email = 'filipe@example.com'), (SELECT id FROM categories WHERE name = 'Sports')),
    ((SELECT id FROM "user" WHERE email = 'filipe@example.com'), (SELECT id FROM categories WHERE name = 'Finance')),
    ((SELECT id FROM "user" WHERE email = 'lucas@example.com'), (SELECT id FROM categories WHERE name = 'Sports')),
    ((SELECT id FROM "user" WHERE email = 'lucas@example.com'), (SELECT id FROM categories WHERE name = 'Finance')),
    ((SELECT id FROM "user" WHERE email = 'lucas@example.com'), (SELECT id FROM categories WHERE name = 'Movies')),
    ((SELECT id FROM "user" WHERE email = 'roseli@example.com'), (SELECT id FROM categories WHERE name = 'Movies'));
