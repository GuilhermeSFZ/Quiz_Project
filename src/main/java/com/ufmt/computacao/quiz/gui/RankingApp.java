/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufmt.computacao.quiz.gui;

/**
 *
 * @author guilherme
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RankingApp extends Application {

    private TableView<RankingEntry> tabela;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Ranking de Pontuação");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        tabela = new TableView<>();
        tabela.setPrefWidth(500);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RankingEntry, String> colUsuario = new TableColumn<>("Usuário");
        colUsuario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUsuario()));
        colUsuario.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black; -fx-font-weight: bold;");

        TableColumn<RankingEntry, Integer> colPontuacao = new TableColumn<>("Pontuação");
        colPontuacao.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPontuacao()).asObject());
        colPontuacao.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black; -fx-font-weight: bold;");

        tabela.getColumns().addAll(colUsuario, colPontuacao);
        tabela.setItems(getRankingReal());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");
        btnVoltar.setPrefWidth(100);
        btnVoltar.setPrefHeight(35);
        btnVoltar.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(lblTitulo, tabela, btnVoltar);

        Scene scene = new Scene(root, 650, 550);
        primaryStage.setTitle("Ranking de Pontuação");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private ObservableList<RankingEntry> getRankingReal() {
        com.ufmt.computacao.quiz.dao.RankingDAO dao = new com.ufmt.computacao.quiz.dao.RankingDAO();
        java.util.List<com.ufmt.computacao.quiz.model.Ranking> resultados = dao.listarTodos();

        return FXCollections.observableArrayList(
            resultados.stream()
                .map(r -> new RankingEntry(
                    r.getUsuario(),
                    r.getPontuacao()
                ))
                .collect(java.util.stream.Collectors.toList())
        );
    }

    // Classe de apoio (modelo de ranking)
    public static class RankingEntry {
        private final String usuario;
        private final int pontuacao;

        public RankingEntry(String usuario, int pontuacao) {
            this.usuario = usuario;
            this.pontuacao = pontuacao;
        }
        
        public String getUsuario() { return usuario; }
        public int getPontuacao() { return pontuacao; }
    }
}

