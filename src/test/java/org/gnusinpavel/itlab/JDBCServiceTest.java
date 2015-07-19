package org.gnusinpavel.itlab;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;
import org.gnusinpavel.itlab.service.impl.JDBCService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class JDBCServiceTest extends Assert {

    private CrudService<Employee> crud;

    @BeforeClass
    public static void beforeClass() {
        DBHelper.clearDb();
    }

    @Before
    public void setUp() throws Exception {
        crud = new JDBCService();
    }

    @Test
    public void testCreate() throws Exception {
        Employee e = new Employee("Ivan", "Ivanov", 25, true);
        e = crud.create(e);
        assertTrue(e.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee employee = crud.create(new Employee("Petr", "Petrov", 30, true));
        employee.setAge(35);
        int update = crud.update(employee);
        assertTrue(update > 0);

        Employee updateEmployee = crud.get(employee.getId());
        assertEquals(35, updateEmployee.getAge());
    }

    @Test
    public void testDelete() throws Exception {
        Employee employee = crud.create(new Employee("Sara", "Connor", 34, false));
        int delete = crud.delete(employee.getId());
        assertTrue(delete > 0);

        Employee deletedEmployee = crud.get(employee.getId());
        assertNull(deletedEmployee);
    }

    @Test
    public void testList() throws Exception {
        DBHelper.clearDb();

        crud.create(new Employee("Sara", "Connor", 34, false));
        crud.create(new Employee("Petr", "Petrov", 30, true));
        crud.create(new Employee("Tom", "Cruise", 48, true));

        List<Employee> list = crud.list();
        assertEquals(3, list.size());
    }

    @Test
    public void testGet() throws Exception {
        Employee employee = crud.create(new Employee("Tom", "Cruise", 48, true));
        Employee getEmployee = crud.get(employee.getId());
        assertEquals(getEmployee, employee);
    }
}
