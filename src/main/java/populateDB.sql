INSERT INTO developers (first_name, last_name, age, gender, email, phone, salary)
VALUES
('Ihor', 'Bztsennyi', 37, 'male', 'e-mail@ukr.net', '063 123 12 32', 1230.25),
('Alex', 'Kondratenko', 47, 'male', 'e-mail@gmail.com', '067 123 66 66', 2305.65),
('Olga', 'Tuz', 25, 'female', 'e-mailolga@ukr.net', '093 321 12 32', 5627.99),
('Tatiana', 'Forest', 29, 'female', 'e-mailtetiana@ukr.net', '067 999 22 55', 1262.44);

INSERT INTO skills (department, developer_name_id, skills_level)
VALUES
('Java', 1, 'Junior'),
('C++', 2, 'Middle'),
('C#', 3, 'Senior'),
('JS', 3, 'Junior');

INSERT INTO companies (company_name, country)
VALUES
('EPAM', 'Ukraine'),
('Altium', 'Ukraine'),
('DataArt', 'Poland'),
('FragLab', 'USA');

INSERT INTO customers (first_name, last_name, age, gender, email, phone)
VALUES
('Bill', 'Francklin', 52, 'male', 'Francklin_e-mail@gmail.com', '1 555 555 555'),
('Denis', 'Kuzmenko', 60, 'male', 'Kuzmenko_e-mail@yahoo.com', '097 333 44 66'),
('Elena', 'Topol', 45, 'female', 'Topol_e-mailolga@ukr.net', '063 222 43 89'),
('Tim', 'Bolton', 50, 'male', 'Bolton_e-mailtetiana@ukr.net', '1 243 589 999');

INSERT INTO projects (name, customer_id, company_id, begin_data)
VALUES
('APP for pets', 3, 2, 1220227200),
('Diagram bilder', 1, 4, 1220337200),
('Sound filter', 2, 1, 1320447300),
('Audio converter', 4, 3, 1430227800);

INSERT INTO projectsToDevelopers (project_id, name_id)
VALUES
(1, 4),
(1, 1),
(1, 3),
(2, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2);

