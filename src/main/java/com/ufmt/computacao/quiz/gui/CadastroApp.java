/**
 *
 * @author Guilherme da Silva Ferraz.
 */

package com.ufmt.computacao.quiz.gui;
import com.ufmt.computacao.quiz.dao.UsuarioDAO;
import com.ufmt.computacao.quiz.model.Usuario;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CadastroApp extends Application {
    private TextField txtEmail;
    private PasswordField txtSenha;
    private CheckBox chkAdministrador;
    private UsuarioDAO usuarioDAO;

    @Override
    public void start(Stage primaryStage) {
        usuarioDAO = new UsuarioDAO();

        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.WHITE, null, null)));

        Label lblTitulo = new Label("Cadastro de Novo Usuário");

        txtEmail = new TextField();
        txtEmail.setPromptText("Login");

        txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");

        chkAdministrador = new CheckBox("Administrador");

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setPrefWidth(120);
        btnCadastrar.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");

        btnCadastrar.setOnAction(e -> cadastrarUsuario(primaryStage));

        root.getChildren().addAll(lblTitulo, txtEmail, txtSenha, chkAdministrador, btnCadastrar);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Cadastro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarUsuario(Stage cadastroStage) {
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        boolean isAdmin = chkAdministrador.isSelected();

        if (!email.isEmpty() && !senha.isEmpty()) {
            Usuario novoUsuario = new Usuario(email, email, senha, isAdmin);
            usuarioDAO.salvar(novoUsuario);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário cadastrado com sucesso!");

            cadastroStage.close(); // Fecha o cadastro
            new UsuarioApp().start(new Stage()); // Volta para login
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Preencha todos os campos!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
