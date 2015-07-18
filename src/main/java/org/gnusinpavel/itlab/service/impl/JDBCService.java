package org.gnusinpavel.itlab.service.impl;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;
import org.postgresql.Driver;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Реализует работу с БД с использованием JDBC
 */
public class JDBCService implements CrudService<Employee> {
    public static Logger logger = Logger.getLogger(JDBCService.class.getName());

    public static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/itlab";
    public static final String CREATE_SQL = "INSERT INTO employee (name, surname, age, gender) VALUES (?, ?, ?, ?) RETURNING id";
    public static final String UPDATE_SQL = "";
    public static final String SELECT_SQL = "";
    public static final String DELETE_SQL = "";

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            logger.info("Could not register driver: " + e.getMessage());
        }
    }

    public Employee create(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("employee can't be null.");
        }
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getSurname());
                statement.setInt(3, employee.getAge());
                statement.setBoolean(4, employee.isMale());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        employee.setId(resultSet.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.info("Database exception: " + e.getMessage());
        }
        return employee;
    }

    public List<Employee> list() {
        return null;
    }

    public Employee update(Employee employee) {
        return null;
    }

    public void delete(long id) {

    }

    @Override
    public Employee get(long id) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, "pavel", "1234");
    }
}
