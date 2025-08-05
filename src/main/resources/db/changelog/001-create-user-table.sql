CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone_number VARCHAR(20)
);

INSERT INTO "user" (name, email, phone_number) VALUES
  ('Alice Silva', 'alice@example.com', '+55 11 91234-5678'),
  ('Bruno Souza', 'bruno@example.com', '+55 31 99876-5432'),
  ('Caio Silva', 'caio@example.com', '+55 11 98765-4321'),
  ('Debora Andrade', 'debora@example.com', '+55 35 99654-3210'),
  ('Filipe Caixeta', 'filipe@example.com', '+55 31 98543-2109'),
  ('Lucas Simao', 'lucas@example.com', '+55 35 99432-1678'),
  ('Roseli Batista', 'roseli@example.com', '+55 31 99123-7890');