/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import com.ufmt.computacao.quiz.dao.QuestaoDAO;
import com.ufmt.computacao.quiz.dao.RankingDAO;
import com.ufmt.computacao.quiz.model.Questao;
import com.ufmt.computacao.quiz.model.Ranking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class QuizApp extends Application {

    private final String nomeUsuario;
    private int index = 0;
    private int pontuacao = 0;
    private ToggleGroup grupo = new ToggleGroup();
    private List<Questao> questoes;
    private Label lblTempo;
    private Label lblPergunta;
    private VBox root;
    private Stage stage;

    // Construtor principal com nome real
    public QuizApp(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    // Construtor default exigido pelo JavaFX (não usado diretamente)
    public QuizApp() {
        this.nomeUsuario = "usuario";
    }

    public void start(Stage stage, String dificuldade) {
        this.stage = stage;
        questoes = new QuestaoDAO().buscarPorDificuldade(dificuldade);
        if (questoes.isEmpty()) {
            mostrarErro("Não há questões disponíveis.");
            return;
        }

        root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        lblTempo = new Label("Tempo restante: 60s"); // Opcional: você pode implementar um timer real depois
        lblPergunta = new Label();
        Button btnProximo = new Button("Próximo");

        btnProximo.setOnAction(e -> verificarResposta());

        root.getChildren().addAll(lblTempo, lblPergunta, btnProximo);
        mostrarQuestao();

        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle("Quiz");
        stage.show();
    }

    private void mostrarQuestao() {
        root.getChildren().removeIf(n -> n instanceof RadioButton);

        if (index < questoes.size()) {
            Questao q = questoes.get(index);
            lblPergunta.setText(q.getEnunciado());

            RadioButton a = new RadioButton(q.getOpcaoA());
            RadioButton b = new RadioButton(q.getOpcaoB());
            RadioButton c = new RadioButton(q.getOpcaoC());
            RadioButton d = new RadioButton(q.getOpcaoD());

            grupo = new ToggleGroup();
            a.setToggleGroup(grupo);
            b.setToggleGroup(grupo);
            c.setToggleGroup(grupo);
            d.setToggleGroup(grupo);

            root.getChildren().addAll(a, b, c, d);
        } else {
            finalizarQuiz();
        }
    }

    private void verificarResposta() {
        if (index >= questoes.size()) return;

        Questao q = questoes.get(index);
        Toggle selecionado = grupo.getSelectedToggle();
        if (selecionado != null) {
            RadioButton r = (RadioButton) selecionado;

            String letraSelecionada = null;

            if (r.getText().equals(q.getOpcaoA())) letraSelecionada = "A";
            else if (r.getText().equals(q.getOpcaoB())) letraSelecionada = "B";
            else if (r.getText().equals(q.getOpcaoC())) letraSelecionada = "C";
            else if (r.getText().equals(q.getOpcaoD())) letraSelecionada = "D";

            if (letraSelecionada != null && letraSelecionada.equalsIgnoreCase(q.getCorreta())) {
                pontuacao += 2;
            }
        }

        index++;
        mostrarQuestao();
    }

    private void finalizarQuiz() {
        // Salva a pontuação no banco com o nome real
        RankingDAO dao = new RankingDAO();
        dao.salvar(new Ranking(nomeUsuario, pontuacao, new Date()));
        
        // Exibe mensagem final
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz finalizado!");
        alert.setHeaderText(null);
        alert.setContentText("Você acertou " + (pontuacao / 2) + " de " + questoes.size() +
                " questões.\nPontuação total: " + pontuacao + " pontos.");
        alert.showAndWait();

        stage.close();
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) {
        throw new UnsupportedOperationException("Use start(Stage, dificuldade, nomeUsuario)");
    }
}
