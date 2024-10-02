package com.vini.crudcicd;

import com.vini.crudcicd.entity.Employee;
import com.vini.crudcicd.repository.EmployeeRepository;
import com.vini.crudcicd.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        List<Employee> employees = employeeService.getAllEmployees();

        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        Assertions.assertNotNull(createdEmployee);
        Assertions.assertEquals("John Doe", createdEmployee.getName());
    }
}
