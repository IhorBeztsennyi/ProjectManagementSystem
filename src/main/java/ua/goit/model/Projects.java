package ua.goit.model;

public class Projects {

    private Integer project_id;
    private String name;
    private Integer customer_id;
    private Integer company_id;
    private Integer begin_data;

    public Projects() {
    }

    public Projects(String name, Integer customer_id, Integer company_id, Integer begin_data) {
        this.name = name;
        this.customer_id = customer_id;
        this.company_id = company_id;
        this.begin_data = begin_data;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getBegin_data() {
        return begin_data;
    }

    public void setBegin_data(Integer begin_data) {
        this.begin_data = begin_data;
    }
}
