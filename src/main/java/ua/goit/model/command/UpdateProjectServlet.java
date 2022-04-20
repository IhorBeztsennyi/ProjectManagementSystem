package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.ProjectsConverter;
import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.ProjectsDto;
import ua.goit.queries.Queries;
import ua.goit.repository.ProjectsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateProject")
public class UpdateProjectServlet extends HttpServlet {

    Queries queries = null;
    ProjectsConverter converter = null;
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
        String name = req.getParameter("name");
        Integer customer_id = Integer.parseInt(req.getParameter("customer_id"));
        Integer company_id = Integer.parseInt(req.getParameter("company_id"));
        Integer begin_data = Integer.parseInt(req.getParameter("begin_data"));
        ProjectsDto projectDto = new ProjectsDto(name, customer_id, company_id, begin_data);
        converter = new ProjectsConverter();
        ProjectsDao project = converter.dtoToDao(projectDto);
        projectsRepository.update(project_id, project);
        req.getRequestDispatcher("/WEB-INF/html/updateProject.jsp").forward(req, resp);
    }
}
