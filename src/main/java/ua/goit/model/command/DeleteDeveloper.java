package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.queries.Queries;
import ua.goit.repository.DevelopersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteDeveloper")
public class DeleteDeveloper extends HttpServlet {
    Queries queries = null;
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
        Integer nameId = Integer.parseInt(req.getParameter("nameId"));
        developersRepository.remove(nameId);
        req.setAttribute("nameId", nameId);
        req.getRequestDispatcher("/WEB-INF/html/deleteDeveloper.jsp").forward(req, resp);
    }
}
