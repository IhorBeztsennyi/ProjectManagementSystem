package ua.goit;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.gueries.Queries;
import ua.goit.model.Customers;
import ua.goit.model.Developers;
import ua.goit.model.Projects;
import ua.goit.repository.CustomersRepository;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.ProjectsRepository;
import ua.goit.view.Console;
import ua.goit.view.View;

public class App {

    public static void main(String[] args) {
        View view = new Console();

        PropertiesUtil util = new PropertiesUtil();
        System.out.println(util.getHostname() + " " + util.getPort() + " " + util.getSchema() + " " + util.getUser() + " " + util.getPassword());
        DataBaseManager managerDB = new PostgreSqlHikari(util.getHostname(), util.getPort(), util.getSchema(), util.getUser(), util.getPassword());

        Queries queries = new Queries(managerDB);

        view.write("Sum of the project: " + queries.getSumByProjectId(1));
        view.write("List of developers: " + queries.getDevelopersByProjectId(1));



        ProjectsRepository projectsRepository = new ProjectsRepository(managerDB);
        DevelopersRepository developersRepository = new DevelopersRepository(managerDB);
        CustomersRepository customersRepository = new CustomersRepository(managerDB);

        Projects newProject = new Projects("Application for sport", 2, 1, 1220327200);
        Developers newDeveloper = new Developers("Petro", "Podolsky", 39, "male", "podolsky_mail@gmail.com", "077 123 88 66", 2351.25);
        Customers newCustomer = new Customers("Chack", "Freeman", 60, "male", "freeman_mail@ukr.net", "1 243 777 999");

//        projectsRepository.save(newProject);
//        developersRepository.save(newDeveloper);
//        customersRepository.save(newCustomer);



    }
}
