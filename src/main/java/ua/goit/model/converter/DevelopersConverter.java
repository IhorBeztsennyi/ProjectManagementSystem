package ua.goit.model.converter;

import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;

public class DevelopersConverter implements Converter<DevelopersDao, DevelopersDto>{
    @Override
    public DevelopersDto daoToDto(DevelopersDao type) {
        DevelopersDto developerDto = new DevelopersDto();
        developerDto.setNameId(type.getNameId());
        developerDto.setFirstName(type.getFirstName());
        developerDto.setLastName(type.getLastName());
        developerDto.setAge(type.getAge());
        developerDto.setGender(type.getGender());
        developerDto.setEmail(type.getEmail());
        developerDto.setPhone(type.getPhone());
        developerDto.setSalary(type.getSalary());
        return developerDto;
    }

    @Override
    public DevelopersDao dtoToDao(DevelopersDto type) {
        DevelopersDao developerDao = new DevelopersDao();
        developerDao.setNameId(type.getNameId());
        developerDao.setFirstName(type.getFirstName());
        developerDao.setLastName(type.getLastName());
        developerDao.setAge(type.getAge());
        developerDao.setGender(type.getGender());
        developerDao.setEmail(type.getEmail());
        developerDao.setPhone(type.getPhone());
        developerDao.setSalary(type.getSalary());
        return developerDao;
    }
}
