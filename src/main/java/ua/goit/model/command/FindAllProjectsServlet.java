package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.DevelopersConverter;
import ua.goit.model.converter.ProjectsConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.model.dto.ProjectsDto;
import ua.goit.queries.Queries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/findAllProjects")
public class FindAllProjectsServlet extends HttpServlet {
    Queries queries = null;
    ProjectsConverter projectsConverter = null;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectsConverter = new ProjectsConverter();
        List<ProjectsDto> projects = queries.getAllProjects().stream()
                .map(projectsConverter::daoToDto)
                .collect(Collectors.toList());
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/html/allProjects.jsp").forward(req, resp);
    }

}
