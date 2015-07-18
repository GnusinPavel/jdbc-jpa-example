package org.gnusinpavel.itlab;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;
import org.gnusinpavel.itlab.service.impl.JDBCService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JDBCServiceTest extends Assert {

    private CrudService<Employee> crud;

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
}
