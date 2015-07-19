package org.gnusinpavel.itlab.service.impl;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;

import javax.persistence.*;
import java.util.List;

public class JPAService implements CrudService<Employee> {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("myUnit");
    private EntityManager em = factory.createEntityManager();

    public Employee create(Employee employee) {
        em.getTransaction().begin();
        try {
            em.persist(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Не удалось создать объект: " + e.getMessage());
        }
        return employee;
    }

    public List<Employee> list() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    public int update(Employee employee) {
        em.getTransaction().begin();
        try {
            em.persist(employee);
            em.getTransaction().commit();
            return 1;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Не удалось обновить объект: " + e.getMessage());
        }
    }

    public int delete(long id) {
        Employee e = get(id);
        if (e != null) {
            em.remove(e);
            return 1;
        }
        return -1;
    }

    @Override
    public Employee get(long id) {
        return em.find(Employee.class, id);

//        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.id = :id", Employee.class);
//        query.setParameter("id", id);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
    }
}
