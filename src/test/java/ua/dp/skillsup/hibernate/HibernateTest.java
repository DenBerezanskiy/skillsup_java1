package ua.dp.skillsup.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistentApplicationContext.xml")
@Transactional
public class HibernateTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    LocalContainerEntityManagerFactoryBean context;

    @Test
    public void testPersistence() throws Exception {
        context.toString();
        em.persist(new TestEntity("one"));
        em.persist(new TestEntity("two"));

        List result = em.createQuery("select t from TestEntity t").getResultList();
        System.out.println(result);
    }
}