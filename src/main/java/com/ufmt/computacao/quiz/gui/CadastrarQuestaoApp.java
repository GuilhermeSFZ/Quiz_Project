/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import com.ufmt.computacao.quiz.dao.QuestaoDAO;
import com.ufmt.computacao.quiz.model.Questao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastrarQuestaoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Cadastro de Questão");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtPergunta = new TextField();
        txtPergunta.setPromptText("Digite a pergunta");

        TextField txtOpcaoA = new TextField();
        txtOpcaoA.setPromptText("Opção A");

        TextField txtOpcaoB = new TextField();
        txtOpcaoB.setPromptText("Opção B");

        TextField txtOpcaoC = new TextField();
        txtOpcaoC.setPromptText("Opção C");

        TextField txtOpcaoD = new TextField();
        txtOpcaoD.setPromptText("Opção D");

        ComboBox<String> cbCorreta = new ComboBox<>();
        cbCorreta.getItems().addAll("A", "B", "C", "D");
        cbCorreta.setPromptText("Correta");

        ComboBox<String> cbDificuldade = new ComboBox<>();
        cbDificuldade.getItems().addAll("Fácil", "Média", "Difícil");
        cbDificuldade.setPromptText("Dificuldade");

        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");
            
        for (Button btn : new Button[]{btnSalvar, btnVoltar}) {
            btn.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");
            btn.setPrefWidth(100);
            btn.setPrefHeight(35);
        }

        btnSalvar.setOnAction(e -> {
            String pergunta = txtPergunta.getText().trim();
            String a = txtOpcaoA.getText().trim();
            String b = txtOpcaoB.getText().trim();
            String c = txtOpcaoC.getText().trim();
            String d = txtOpcaoD.getText().trim();
            String correta = cbCorreta.getValue();
            String dificuldade = cbDificuldade.getValue();

            if (pergunta.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || correta == null || dificuldade == null) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Preencha todos os campos!");
                return;
            }

            // Cria a questão
            Questao questao = new Questao(pergunta, a, b, c, d, correta, dificuldade);

            // Salva no banco
            try {
                QuestaoDAO questaoDAO = new QuestaoDAO();
                questaoDAO.salvar(questao);
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Questão cadastrada com sucesso!");
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao salvar no banco de dados.");
            }

            // Limpa os campos
            txtPergunta.clear(); txtOpcaoA.clear(); txtOpcaoB.clear();
            txtOpcaoC.clear(); txtOpcaoD.clear();
            cbCorreta.setValue(null); cbDificuldade.setValue(null);
        });


        btnVoltar.setOnAction(e -> primaryStage.close());

        HBox botoes = new HBox(10, btnSalvar, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        root.getChildren().addAll(lblTitulo, txtPergunta, txtOpcaoA, txtOpcaoB, txtOpcaoC, txtOpcaoD, cbCorreta, cbDificuldade, botoes);

        Scene scene = new Scene(root, 600, 650);
        primaryStage.setTitle("Cadastro de Questão");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}