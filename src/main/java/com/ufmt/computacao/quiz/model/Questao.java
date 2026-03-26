/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.model;
import jakarta.persistence.*;

@Entity
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enunciado;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private String opcaoD;
    private String correta; // Ex: "A", "B", etc.
    private String dificuldade; // Ex: "Fácil", "Média", "Difícil"

    public Questao() {}

    public Questao(String enunciado, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String correta, String dificuldade) {
        this.enunciado = enunciado;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.correta = correta;
        this.dificuldade = dificuldade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String getOpcaoA() { return opcaoA; }
    public void setOpcaoA(String opcaoA) { this.opcaoA = opcaoA; }

    public String getOpcaoB() { return opcaoB; }
    public void setOpcaoB(String opcaoB) { this.opcaoB = opcaoB; }

    public String getOpcaoC() { return opcaoC; }
    public void setOpcaoC(String opcaoC) { this.opcaoC = opcaoC; }

    public String getOpcaoD() { return opcaoD; }
    public void setOpcaoD(String opcaoD) { this.opcaoD = opcaoD; }

    public String getCorreta() { return correta; }
    public void setCorreta(String correta) { this.correta = correta; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }

    @Override
    public String toString() {
        return id + " - " + enunciado + " [" + dificuldade + "]";
    }
}

