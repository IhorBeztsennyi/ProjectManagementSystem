package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.DevelopersConverter;
import ua.goit.model.converter.ProjectsConverter;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.queries.Queries;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.ProjectsRepository;

import javax.management.MXBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/saveNewProjectsForm")
public class SaveNewProjectFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/saveNewProjectForm.jsp").forward(req, resp);
    }
}
