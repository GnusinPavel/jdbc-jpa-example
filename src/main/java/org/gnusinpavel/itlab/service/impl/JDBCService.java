package org.gnusinpavel.itlab.service.impl;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Реализует работу с БД с использованием JDBC
 */
public class JDBCService implements CrudService<Employee> {
    public static Logger logger = Logger.getLogger(JDBCService.class.getName());

    public static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/itlab";
    public static final String CREATE_SQL = "INSERT INTO employee (name, surname, age, gender) VALUES (?, ?, ?, ?) RETURNING id";
    public static final String UPDATE_SQL = "UPDATE employee SET name = ?, surname = ?, age = ?, gender = ? WHERE id = ?";
    public static final String LIST_SQL = "SELECT id, name, surname, age, gender FROM employee";
    public static final String GET_SQL = "SELECT id, name, surname, age, gender FROM employee WHERE id = ?";
    public static final String DELETE_SQL = "DELETE FROM employee WHERE id = ?";

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
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(LIST_SQL)) {
                    List<Employee> employees = new ArrayList<>();
                    while (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        String name = resultSet.getString(2);
                        String surname = resultSet.getString(3);
                        int age = resultSet.getInt(4);
                        boolean male = resultSet.getBoolean(5);
                        Employee e = new Employee(name, surname, age, male);
                        e.setId(id);
                        employees.add(e);
                    }
                    return employees;
                }
            }
        } catch (SQLException e) {
            logger.info("Database exception: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public int update(Employee employee) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getSurname());
                statement.setInt(3, employee.getAge());
                statement.setBoolean(4, employee.isMale());
                statement.setLong(5, employee.getId());
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.info("Database exception: " + e.getMessage());
        }
        return -1;
    }

    public int delete(long id) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
                statement.setLong(1, id);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.info("Database exception: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Employee get(long id) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_SQL)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString(2);
                        String surname = resultSet.getString(3);
                        int age = resultSet.getInt(4);
                        boolean male = resultSet.getBoolean(5);
                        Employee e = new Employee(name, surname, age, male);
                        e.setId(id);
                        return e;
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.info("Database exception: " + e.getMessage());
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, "pavel", "1234");
    }
}
