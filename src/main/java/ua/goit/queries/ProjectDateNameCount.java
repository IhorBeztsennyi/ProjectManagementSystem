package ua.goit.queries;

import java.time.Instant;
import java.time.ZoneId;

public class ProjectDateNameCount {

    private Integer begin_data;
    private String name;
    private Integer countOfDevelopers;

    public ProjectDateNameCount() {
    }

    public void setBegin_data(Integer begin_data) {
        this.begin_data = begin_data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountOfDevelopers(Integer countOfDevelopers) {
        this.countOfDevelopers = countOfDevelopers;
    }

    public String getBegin_data() {
        return Instant.ofEpochSecond(begin_data).atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    public String getName() {
        return name;
    }

    public Integer getCountOfDevelopers() {
        return countOfDevelopers;
    }

    @Override
    public String toString() {
        return "ProjectDateNameCount{" +
                "begin_data=" + Instant.ofEpochSecond(begin_data).atZone(ZoneId.systemDefault()).toLocalDate() +
                ", name='" + name + '\'' +
                ", countOfDevelopers=" + countOfDevelopers +
                '}';
    }
}
