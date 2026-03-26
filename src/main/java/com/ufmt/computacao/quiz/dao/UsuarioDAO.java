/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.dao;
import com.ufmt.computacao.quiz.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class UsuarioDAO {
    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
    }

    public void salvar(Usuario usuario) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
        }
    }

    public void atualizar(Usuario usuario) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(usuario);
            session.getTransaction().commit();
        }
    }

    public void excluir(Usuario usuario) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(usuario);
            session.getTransaction().commit();
        }
    }

    public List<Usuario> listarTodos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        }
    }

    public Usuario buscarPorId(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public Usuario autenticar(String email, String senha) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email AND senha = :senha", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .uniqueResult();
        }
    }

    public void close() {
        factory.close();
    }
}