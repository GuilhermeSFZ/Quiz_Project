/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PainelAdminApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Painel do Administrador");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnCadastrarQuestao = new Button("Cadastrar Nova Questão");
        Button btnVisualizarQuestoes = new Button("Visualizar Questões");
        Button btnVerRanking = new Button("Ver Ranking");
        Button btnLogout = new Button("Logout");

        // Estilização dos botões
        for (Button btn : new Button[]{btnCadastrarQuestao, btnVisualizarQuestoes, btnVerRanking, btnLogout}) {
            btn.setPrefWidth(220);
            btn.setPrefHeight(40);
            btn.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        // Ações dos botões
        btnCadastrarQuestao.setOnAction(e -> {
            try {
                new CadastrarQuestaoApp().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        btnVisualizarQuestoes.setOnAction(e -> {
            try {
                new VisualizarQuestoesApp().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        btnVerRanking.setOnAction(e -> {
            try {
                new RankingApp().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnLogout.setOnAction(e -> {
            try {
                new UsuarioApp().start(new Stage()); // Volta para tela de login
                primaryStage.close(); // Fecha painel admin
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(lblTitulo, btnCadastrarQuestao, btnVisualizarQuestoes, btnVerRanking, btnLogout);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Painel do Administrador");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
