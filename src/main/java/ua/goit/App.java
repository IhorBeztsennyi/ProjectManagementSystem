package ua.goit;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.queries.Queries;
import ua.goit.model.dao.CustomersDao;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dao.ProjectsDao;
import ua.goit.repository.CustomersRepository;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.ProjectsRepository;
import ua.goit.view.Console;
import ua.goit.view.View;

public class App {

    public static void main(String[] args) {
        View view = new Console();

        PropertiesUtil util = new PropertiesUtil();
        DataBaseManager managerDB = new PostgreSqlHikari(util.getHostname(), util.getPort(), util.getSchema(), util.getUser(), util.getPassword());
        Queries queries = new Queries(managerDB);

        view.write("Sum of the project: " + queries.getSumByProjectId(1));
        view.write("List of developers: " + queries.getDevelopersByProjectId(1));
        view.write("List of Java developers: " + queries.getJavaDevelopers());
        view.write("List of Middle developers: " + queries.getMiddleDevelopers());
        view.write("List of projects (begin data, name and a count of developers): " + queries.getCountOfDevelopers());

        ProjectsRepository projectsRepository = new ProjectsRepository(managerDB);
        DevelopersRepository developersRepository = new DevelopersRepository(managerDB);
        CustomersRepository customersRepository = new CustomersRepository(managerDB);

        ProjectsDao newProject = new ProjectsDao("Application for sport", 2, 1, 1220327200);
        DevelopersDao newDeveloper = new DevelopersDao("Petro", "Podolsky", 39, "male", "podolsky_mail@gmail.com", "077 123 88 66", 2351.25);
        CustomersDao newCustomer = new CustomersDao("Chack", "Freeman", 60, "male", "freeman_mail@ukr.net", "1 243 777 999");

//        projectsRepository.save(newProject);
//        developersRepository.save(newDeveloper);
//        customersRepository.save(newCustomer);
    }
}
