CREATE TABLE expense (
id bigserial PRIMARY KEY,
type VARCHAR(20),
amount NUMERIC(100,2),
expense_date DATE,
user_id bigserial
);