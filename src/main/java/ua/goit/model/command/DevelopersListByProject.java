package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.DevelopersConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.queries.Queries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/developersListByProject")
public class DevelopersListByProject extends HttpServlet {

    Queries queries = null;
    DevelopersConverter developersConverter = null;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer projectNumber = Integer.parseInt(req.getParameter("projectNumber"));
        developersConverter = new DevelopersConverter();
        List<DevelopersDto> developers = queries.getDevelopersByProjectId(projectNumber).stream()
                .map(developersConverter::daoToDto)
                .collect(Collectors.toList());
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/html/developersListByProject.jsp").forward(req, resp);
    }
}
