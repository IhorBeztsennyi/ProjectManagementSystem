package ua.goit;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.CustomersConverter;
import ua.goit.model.converter.DevelopersConverter;
import ua.goit.model.converter.ProjectsConverter;
import ua.goit.queries.Queries;
import ua.goit.view.Console;
import ua.goit.view.View;

public class App {

    public static void main(String[] args) {
        View view = new Console();

        DevelopersConverter developersConverter = new DevelopersConverter();
        CustomersConverter customersConverter = new CustomersConverter();
        ProjectsConverter projectsConverter = new ProjectsConverter();

        PropertiesUtil util = new PropertiesUtil();
        DataBaseManager managerDB = new PostgreSqlHikari(util.getHostname(), util.getPort(), util.getSchema(), util.getUser(), util.getPassword(), util.getJdbcDriver());
        Queries queries = new Queries(managerDB);

        view.write("Sum of the project: " + queries.getSumByProjectId(1));
        view.write("List of developers: " + queries.getDevelopersByProjectId(1));
        view.write("List of Java developers: " + queries.getJavaDevelopers());
        view.write("List of Middle developers: " + queries.getMiddleDevelopers());
        view.write("List of projects (begin data, name and a count of developers): " + queries.getCountOfDevelopers());
        view.write("List of All developers: " + queries.getAllDevelopers());
//
//        ProjectsRepository projectsRepository = new ProjectsRepository(managerDB);
//        DevelopersRepository developersRepository = new DevelopersRepository(managerDB);
//        CustomersRepository customersRepository = new CustomersRepository(managerDB);
//
//        DevelopersDto newDeveloper = new DevelopersDto("Petro", "Podolsky", 39, "male", "podolsky_mail@gmail.com", "077 123 88 66", 2351.25);
//        CustomersDto newCustomer = new CustomersDto("Chack", "Freeman", 60, "male", "freeman_mail@ukr.net", "1 243 777 999");
//        ProjectsDto newProject = new ProjectsDto("Application for sport", 2, 1, 1220327200);

//        developersRepository.save(developersConverter.dtoToDao(newDeveloper));
//        customersRepository.save(customersConverter.dtoToDao(newCustomer));
//        projectsRepository.save(projectsConverter.dtoToDao(newProject));
//
//        view.write("Developers update:");
//        DevelopersDto updatedDeveloper = new DevelopersDto("Petro", "Podolsky", 39, "male", "podolsky_mail@gmail.com", "077 123 88 66", 2500.00);
//        view.write("Was updated "+ developersRepository.update(5, developersConverter.dtoToDao(updatedDeveloper))+ " row");
//
//        view.write("Developers remove");
//        developersRepository.remove(5);
//
//        view.write("Customers update:");
//        CustomersDto updatedCustomer = new CustomersDto("Chack", "Freeman", 61, "male", "freeman_mail@ukr.net", "1 243 777 999");
//        view.write("Was updated " + customersRepository.update(5, customersConverter.dtoToDao(updatedCustomer)) + " row");
//
//        view.write("Customer remove");
//        customersRepository.remove(5);

//        view.write("Project update:");
//        ProjectsDto updatedProject = new ProjectsDto("Application for sport", 2, 1, 1220327200);
//        view.write("Was updated " + projectsRepository.update(5, projectsConverter.dtoToDao(updatedProject)) + " row");
//
//        view.write("Project remove");
//        projectsRepository.remove(5);



    }
}
