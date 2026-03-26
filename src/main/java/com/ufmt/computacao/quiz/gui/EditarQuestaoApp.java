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

public class EditarQuestaoApp extends Application{

    public void start(Stage stage, Questao questao) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Editar Questão");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtPergunta = new TextField(questao.getEnunciado());
        txtPergunta.setPromptText("Digite a pergunta");

        TextField txtOpcaoA = new TextField(questao.getOpcaoA());
        txtOpcaoA.setPromptText("Opção A");

        TextField txtOpcaoB = new TextField(questao.getOpcaoB());
        txtOpcaoB.setPromptText("Opção B");

        TextField txtOpcaoC = new TextField(questao.getOpcaoC());
        txtOpcaoC.setPromptText("Opção C");

        TextField txtOpcaoD = new TextField(questao.getOpcaoD());
        txtOpcaoD.setPromptText("Opção D");

        ComboBox<String> cbCorreta = new ComboBox<>();
        cbCorreta.getItems().addAll("A", "B", "C", "D");
        cbCorreta.setValue(questao.getCorreta());
        cbCorreta.setPromptText("Correta");

        ComboBox<String> cbDificuldade = new ComboBox<>();
        cbDificuldade.getItems().addAll("Fácil", "Média", "Difícil");
        cbDificuldade.setValue(questao.getDificuldade());
        cbDificuldade.setPromptText("Dificuldade");

        Button btnSalvar = new Button("Salvar Alterações");
        Button btnVoltar = new Button("Voltar");

        for (Button btn : new Button[]{btnSalvar, btnVoltar}) {
            btn.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");
            btn.setPrefWidth(140);
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

            // Atualiza os dados da questão
            questao.setEnunciado(pergunta);
            questao.setOpcaoA(a);
            questao.setOpcaoB(b);
            questao.setOpcaoC(c);
            questao.setOpcaoD(d);
            questao.setCorreta(correta);
            questao.setDificuldade(dificuldade);

            try {
                QuestaoDAO dao = new QuestaoDAO();
                dao.atualizar(questao);
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Questão atualizada!");
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao atualizar a questão.");
            }
        });

        btnVoltar.setOnAction(e -> stage.close());

        HBox botoes = new HBox(10, btnSalvar, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        root.getChildren().addAll(lblTitulo, txtPergunta, txtOpcaoA, txtOpcaoB, txtOpcaoC, txtOpcaoD, cbCorreta, cbDificuldade, botoes);

        Scene scene = new Scene(root, 500, 580);
        stage.setTitle("Editar Questão");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

