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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UsuarioApp extends Application {
    private TextField txtEmail;
    private PasswordField txtSenha;
    private UsuarioDAO usuarioDAO;

    @Override
    public void start(Stage primaryStage) {
        usuarioDAO = new UsuarioDAO();

        // Contêiner principal (HBox para dividir a tela)
        HBox root = new HBox();
        root.setPrefSize(600, 400);

        // Lado esquerdo: Fundo com a imagem background.png
        Pane leftPane = new Pane();
        leftPane.setPrefSize(300, 400);

        // Carrega a imagem do caminho com.ufmt.computacao.quiz.images
        Image backgroundImage = new Image("background.png");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        leftPane.setBackground(new Background(bgImage));

        // Lado direito: Formulário de login
        VBox loginPane = new VBox(15);
        loginPane.setPrefSize(300, 400);
        loginPane.setPadding(new Insets(30));
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        // Campo de email
        Label lblEmail = new Label("Login:");
        txtEmail = new TextField();
        txtEmail.setPromptText("Digite seu email");
        txtEmail.setPrefWidth(250);

        // Campo de senha
        Label lblSenha = new Label("Senha:");
        txtSenha = new PasswordField();
        txtSenha.setPromptText("Digite sua senha");
        txtSenha.setPrefWidth(250);

        // Botões
        Button btnEntrar = new Button("Entrar");
        btnEntrar.setPrefWidth(120);
        btnEntrar.setStyle("-fx-background-color: #C88A36; -fx-text-fill: white; -fx-font-weight: bold;");

        Button btnCadastrar = new Button("Cadastrar-se");
        btnCadastrar.setPrefWidth(120);
        btnCadastrar.setStyle("-fx-background-color: #C88A36; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-border-color: #C88A36; -fx-border-width: 1;");

        // Adiciona componentes ao loginPane
        loginPane.getChildren().addAll(lblEmail, txtEmail, lblSenha, txtSenha, btnEntrar, btnCadastrar);

        // Adiciona os painéis ao root
        root.getChildren().addAll(leftPane, loginPane);

        // Ações dos botões
        btnEntrar.setOnAction(e -> fazerLogin(primaryStage));
        btnCadastrar.setOnAction(e -> abrirTelaCadastro(primaryStage));

        // Configura a cena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Login do Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fazerLogin(Stage loginStage) {
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        if (!email.isEmpty() && !senha.isEmpty()) {
            Usuario usuario = usuarioDAO.autenticar(email, senha);
            if (usuario != null) {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Login bem-sucedido! Bem-vindo, " + usuario.getNome() + "!");

                if (usuario.isAdministrador()) {
                    // Se for ADMINISTRADOR, abre o Painel de Administração
                    try {
                        new PainelAdminApp().start(new Stage());
                        loginStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        new EscolherDificuldadeApp(usuario.getNome()).start(new Stage());
                        loginStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Email ou senha inválidos!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Preencha todos os campos!");
        }
    }


    private void abrirTelaCadastro(Stage loginStage) {
        try {
            new CadastroApp().start(new Stage());
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}