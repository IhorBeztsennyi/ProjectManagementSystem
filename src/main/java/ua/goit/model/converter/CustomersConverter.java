package ua.goit.model.converter;

import ua.goit.model.dao.CustomersDao;
import ua.goit.model.dto.CustomersDto;

public class CustomersConverter implements Converter<CustomersDao, CustomersDto> {

    @Override
    public CustomersDto daoToDto(CustomersDao type) {
        CustomersDto customerDto = new CustomersDto();
        customerDto.setCustomer_id(type.getCustomer_id());
        customerDto.setFirst_name(type.getFirst_name());
        customerDto.setLast_name(type.getLast_name());
        customerDto.setAge(type.getAge());
        customerDto.setGender(type.getGender());
        customerDto.setEmail(type.getEmail());
        customerDto.setPhone(type.getPhone());
        return customerDto;
    }

    @Override
    public CustomersDao dtoToDao(CustomersDto type) {
        CustomersDao customerDao = new CustomersDao();
        customerDao.setCustomer_id(type.getCustomer_id());
        customerDao.setFirst_name(type.getFirst_name());
        customerDao.setLast_name(type.getLast_name());
        customerDao.setAge(type.getAge());
        customerDao.setGender(type.getGender());
        customerDao.setEmail(type.getEmail());
        customerDao.setPhone(type.getPhone());
        return customerDao;
    }
}
