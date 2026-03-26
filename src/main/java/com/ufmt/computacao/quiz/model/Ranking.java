/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private int pontuacao;

    public Ranking() {}

    public Ranking(String usuario, int pontuacao, Date data) {
        this.usuario = usuario;
        this.pontuacao = pontuacao;
    }

    public Long getId() { return id; }
    public String getUsuario() { return usuario; }
    public int getPontuacao() { return pontuacao; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }
}
