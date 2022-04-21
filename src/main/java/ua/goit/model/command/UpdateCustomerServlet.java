package ua.goit.model.command;

import ua.goit.configuration.DataBaseManager;
import ua.goit.configuration.PostgreSqlHikari;
import ua.goit.configuration.PropertiesUtil;
import ua.goit.model.converter.CustomersConverter;
import ua.goit.model.dto.CustomersDto;
import ua.goit.queries.Queries;
import ua.goit.repository.CustomersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    Queries queries = null;
    CustomersConverter converter = null;
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
        int id = Integer.parseInt(req.getParameter("customer_id"));
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        CustomersDto customersDto = new CustomersDto(firstName, lastName, age, gender, email, phone);
        converter = new CustomersConverter();
        Integer number = customersRepository.update(id, converter.dtoToDao(customersDto));
        req.setAttribute("count", number);
        req.getRequestDispatcher("/WEB-INF/html/updateCustomer.jsp").forward(req, resp);
    }
}
