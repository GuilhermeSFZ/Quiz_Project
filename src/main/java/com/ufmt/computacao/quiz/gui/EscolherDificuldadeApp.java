/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EscolherDificuldadeApp extends Application {
    private final String nomeUsuario;

    public EscolherDificuldadeApp(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public EscolherDificuldadeApp() {
        this.nomeUsuario = "usuario"; // fallback (não será mais usado)
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label lbl = new Label("Escolha a dificuldade:");
        ComboBox<String> cbDificuldade = new ComboBox<>();
        cbDificuldade.getItems().addAll("Fácil", "Média", "Difícil");
        cbDificuldade.setPromptText("Selecione");

        Button btnIniciar = new Button("Iniciar Quiz");
        btnIniciar.setStyle("-fx-background-color: #C88A36; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px;");
        btnIniciar.setOnAction(e -> {
            String dificuldade = cbDificuldade.getValue();
            if (dificuldade != null) {
                new QuizApp(nomeUsuario).start(new Stage(), dificuldade);
                stage.close();
            } else {
                showAlerta("Selecione uma dificuldade.");
            }
        });

        root.getChildren().addAll(lbl, cbDificuldade, btnIniciar);
        stage.setScene(new Scene(root, 500, 400));
        stage.setTitle("Escolha de Dificuldade");
        stage.show();
    }

    private void showAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
