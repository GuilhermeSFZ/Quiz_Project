/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.dao;
import com.ufmt.computacao.quiz.model.Ranking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RankingDAO {
    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ranking.class)
                .buildSessionFactory();
    }

    public void salvar(Ranking ranking) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            // Verificar se o usuário já existe no ranking
            Ranking existente = session.createQuery("FROM Ranking WHERE usuario = :usuario", Ranking.class)
                    .setParameter("usuario", ranking.getUsuario())
                    .uniqueResult();

            if (existente != null) {
                // Somar a nova pontuação à existente
                existente.setPontuacao(existente.getPontuacao() + ranking.getPontuacao());
                session.merge(existente);
            } else {
                // Criar novo registro se o usuário não existe
                session.persist(ranking);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ranking> listarTodos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Ranking ORDER BY pontuacao DESC", Ranking.class).list();
        }
    }
}