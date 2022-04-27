ALTER TABLE projectsToDevelopers
DROP CONSTRAINT projectstodevelopers_name_id_fkey,
ADD CONSTRAINT projectstodevelopers_name_id_fkey
   FOREIGN KEY (project_id)
   REFERENCES projects (project_id)
   ON DELETE CASCADE;

ALTER TABLE projectsToDevelopers
DROP CONSTRAINT projectstodevelopers_project_id_fkey,
ADD CONSTRAINT projectstodevelopers_project_id_fkey
   FOREIGN KEY (project_id)
   REFERENCES projects (project_id)
   ON DELETE CASCADE;

ALTER TABLE projects
DROP CONSTRAINT projects_customer_id_fkey,
ADD CONSTRAINT projects_customer_id_fkey
   FOREIGN KEY (customer_id)
   REFERENCES customers (customer_id)
   ON DELETE CASCADE;

ALTER TABLE projects
DROP CONSTRAINT projects_company_id_fkey,
ADD CONSTRAINT projects_company_id_fkey
   FOREIGN KEY (company_id)
   REFERENCES companies (company_id)
   ON DELETE CASCADE;