package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.CustomersConverter;
import ua.goit.model.dto.CustomersDto;
import ua.goit.queries.Queries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/findAllCustomers")
public class FindAllCustomersServlet extends HttpServlet {

    Queries queries = null;
    CustomersConverter customersConverter = null;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DataBaseManager dbConnector = new PostgreSqlHikari(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        queries = new Queries(dbConnector);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customersConverter = new CustomersConverter();
        List<CustomersDto> customers = queries.getAllCustomers().stream()
                .map(customersConverter::daoToDto)
                .collect(Collectors.toList());
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/html/allCustomers.jsp").forward(req, resp);
    }
}
