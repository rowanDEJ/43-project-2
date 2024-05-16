module com.assistant.aiassistant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.assistant.aiassistant to javafx.fxml;
    exports com.assistant.aiassistant;
}