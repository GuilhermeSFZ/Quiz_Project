/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import com.ufmt.computacao.quiz.dao.QuestaoDAO;
import com.ufmt.computacao.quiz.model.Questao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VisualizarQuestoesApp extends Application {

    private TableView<Questao> tabela;
    private ObservableList<Questao> listaQuestoes;
    private QuestaoDAO questaoDAO;

    @Override
    public void start(Stage primaryStage) {
        questaoDAO = new QuestaoDAO();
        listaQuestoes = FXCollections.observableArrayList(questaoDAO.listarTodos());

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Questões cadastradas");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        tabela = new TableView<>();
        tabela.setPrefWidth(600);
        tabela.setItems(listaQuestoes);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Questao, String> colEnunciado = new TableColumn<>("Enunciado");
        colEnunciado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEnunciado()));

        TableColumn<Questao, String> colDificuldade = new TableColumn<>("Dificuldade");
        colDificuldade.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDificuldade()));

        tabela.getColumns().addAll(colEnunciado, colDificuldade);

        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnVoltar = new Button("Voltar");
        for (Button btn : new Button[]{btnEditar, btnExcluir, btnVoltar}) {
            btn.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");
            btn.setPrefWidth(100);
            btn.setPrefHeight(35);
        }

        btnEditar.setOnAction(e -> {
            Questao selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                Stage stage = new Stage();
                new EditarQuestaoApp().start(stage, selecionada);
            } else {
                showAlert(Alert.AlertType.WARNING, "Atenção", "Selecione uma questão para editar.");
            }
        });


        btnExcluir.setOnAction(e -> {
            Questao selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                questaoDAO.excluir(selecionada);
                listaQuestoes.remove(selecionada); // remove da interface
                showAlert(Alert.AlertType.INFORMATION, "Exclusão", "Questão removida com sucesso.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Atenção", "Selecione uma questão para excluir.");
            }
        });

        btnVoltar.setOnAction(e -> primaryStage.close());

        HBox botoes = new HBox(10, btnEditar, btnExcluir, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        root.getChildren().addAll(lblTitulo, tabela, botoes);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Visualizar Questões");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
