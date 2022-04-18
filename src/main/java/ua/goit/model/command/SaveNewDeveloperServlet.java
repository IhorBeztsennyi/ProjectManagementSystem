package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.DevelopersConverter;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.queries.Queries;
import ua.goit.repository.DevelopersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/saveNewDeveloper")
public class SaveNewDeveloperServlet extends HttpServlet {
    Queries queries = null;
    DevelopersConverter converter = null;
    DevelopersRepository developersRepository = null;


    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
        developersRepository = new DevelopersRepository(dbConnector);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Double salary = Double.parseDouble(req.getParameter("salary"));
        DevelopersDto developerDto = new DevelopersDto(firstName, lastName, age, gender, email, phone, salary);
        converter = new DevelopersConverter();
        DevelopersDao developer = converter.dtoToDao(developerDto);
        developersRepository.save(developer);
        req.getRequestDispatcher("/WEB-INF/html/saveNewDeveloper.jsp").forward(req, resp);

    }


}
