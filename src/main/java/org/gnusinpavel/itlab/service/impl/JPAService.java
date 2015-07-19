package org.gnusinpavel.itlab.service.impl;

import org.gnusinpavel.itlab.entity.Employee;
import org.gnusinpavel.itlab.service.CrudService;

import javax.persistence.*;
import java.util.List;

public class JPAService implements CrudService<Employee> {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("myUnit");

    public Employee create(Employee employee) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Не удалось создать объект: " + e.getMessage());
        } finally {
            em.close();
        }
        return employee;
    }

    public List<Employee> list() {
        EntityManager em = getEm();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int update(Employee employee) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            if (!em.contains(employee)) {
                em.merge(employee);
            }
            em.getTransaction().commit();
            return 1;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Не удалось обновить объект: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public int delete(long id) {
        EntityManager em = getEm();
        try {
            Employee e = get(id);
            em.getTransaction().begin();
            if (e != null) {
                e = em.merge(e);
                em.remove(e);
                em.getTransaction().commit();
                return 1;
            }
            return -1;
        } finally {
            em.close();
        }
    }

    @Override
    public Employee get(long id) {
        EntityManager em = getEm();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }

//        EntityManager em = getEm();
//        try {
//            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.id = :id", Employee.class);
//            query.setParameter("id", id);
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            em.close();
//        }
    }

    private EntityManager getEm() {
        return factory.createEntityManager();
    }
}
