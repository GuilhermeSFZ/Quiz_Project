module com.ufmt.computacao.quiz.quiz_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    opens com.ufmt.computacao.quiz.gui to javafx.fxml;
    exports com.ufmt.computacao.quiz.gui;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
}
