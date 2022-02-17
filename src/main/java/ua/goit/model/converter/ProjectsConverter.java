package ua.goit.model.converter;

import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.ProjectsDto;

public class ProjectsConverter implements Converter<ProjectsDao, ProjectsDto>{
    @Override
    public ProjectsDto daoToDto(ProjectsDao type) {
        ProjectsDto projectDto = new ProjectsDto();
        projectDto.setProject_id(type.getProject_id());
        projectDto.setName(type.getName());
        projectDto.setCustomer_id(type.getCustomer_id());
        projectDto.setCompany_id(type.getCompany_id());
        projectDto.setBegin_data(type.getBegin_data());
        return projectDto;
    }

    @Override
    public ProjectsDao dtoToDao(ProjectsDto type) {
        ProjectsDao projectDao = new ProjectsDao();
        projectDao.setProject_id(type.getProject_id());
        projectDao.setName(type.getName());
        projectDao.setCustomer_id(type.getCustomer_id());
        projectDao.setCompany_id(type.getCompany_id());
        projectDao.setBegin_data(type.getBegin_data());
        return projectDao;
    }
}
