package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.queries.Queries;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.ProjectsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    Queries queries = null;
    ProjectsRepository projectsRepository = null;


    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
        projectsRepository = new ProjectsRepository(dbConnector);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer project_id = Integer.parseInt(req.getParameter("project_id"));
        projectsRepository.remove(project_id);
        req.setAttribute("project_id", project_id);
        req.getRequestDispatcher("/WEB-INF/html/deleteProject.jsp").forward(req, resp);
    }
}
