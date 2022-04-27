CREATE TABLE developers (
 name_id SERIAL NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 age SMALLINT NOT NULL,
 gender VARCHAR(6) NOT NULL,
 email  VARCHAR(50) NOT NULL,
 phone VARCHAR(15) NOT NULL,
 salary decimal(12,2),
 PRIMARY KEY (name_id)
);

CREATE TABLE skills (
 skills_id SERIAL NOT NULL,
 department  VARCHAR(50) NOT NULL,
 developer_name_id SERIAL NOT NULL,
 skills_level VARCHAR(50) NOT NULL,
 PRIMARY KEY (skills_id),
 FOREIGN KEY (developer_name_id) REFERENCES developers (name_id)
);

CREATE TABLE companies (
 company_id SERIAL NOT NULL,
 company_name VARCHAR(50) NOT NULL,
 country VARCHAR(50) NOT NULL,
 PRIMARY KEY (company_id)
);

CREATE TABLE customers (
 customer_id SERIAL NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 age SMALLINT,
 gender VARCHAR(6),
 email VARCHAR(50) NOT NULL,
 phone VARCHAR(50) NOT NULL,
 PRIMARY KEY (customer_id)
);

CREATE TABLE projects (
 project_id SERIAL NOT NULL,
 name VARCHAR(50) NOT NULL,
 customer_id SERIAL NOT NULL,
 company_id SERIAL NOT NULL,
 begin_data INT NOT NULL,
 PRIMARY KEY (project_id),
 FOREIGN KEY (company_id) REFERENCES companies (company_id),
 FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE projectsToDevelopers (
 project_id SERIAL NOT NULL,
 name_id SERIAL NOT NULL,
 FOREIGN KEY (project_id) REFERENCES projects (project_id),
 FOREIGN KEY (name_id) REFERENCES developers (name_id)
);

