/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.dao;
import com.ufmt.computacao.quiz.model.Questao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QuestaoDAO {
    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Questao.class)
                .buildSessionFactory();
    }

    public void salvar(Questao questao) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(questao);
            session.getTransaction().commit();
        }
    }

    public void atualizar(Questao questao) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(questao);
            session.getTransaction().commit();
        }
    }

    public void excluir(Questao questao) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(questao);
            session.getTransaction().commit();
        }
    }

    public List<Questao> listarTodos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Questao", Questao.class).list();
        }
    }

    public Questao buscarPorId(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(Questao.class, id);
        }
    }
    public List<Questao> buscarPorDificuldade(String dificuldade) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Questao WHERE dificuldade = :dif", Questao.class)
                          .setParameter("dif", dificuldade)
                          .list();
        }
    }


    public void close() {
        factory.close();
    }
}

