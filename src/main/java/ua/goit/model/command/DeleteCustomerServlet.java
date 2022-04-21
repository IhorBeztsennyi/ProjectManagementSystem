package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.queries.Queries;
import ua.goit.repository.CustomersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    Queries queries = null;
    CustomersRepository customersRepository = null;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
        customersRepository = new CustomersRepository(dbConnector);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer customer_id = Integer.parseInt(req.getParameter("customer_id"));
        customersRepository.remove(customer_id);
        req.setAttribute("customer_id", customer_id);
        req.getRequestDispatcher("/WEB-INF/html/deleteCustomer.jsp").forward(req, resp);
    }
}
