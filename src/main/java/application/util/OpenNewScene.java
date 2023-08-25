package application.util;

import static application.util.Setting.DOCK_VIEW_FXML;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenNewScene {
	public static FXMLLoader inOldWindow(String sceneFXML, String styleSheeString, ActionEvent event,
			Object controller) throws IOException {
		FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(sceneFXML));
		Parent newScreenRoot = loader.load();
		//Parent newScreenRoot = FXMLLoader.load(controller.getClass().getResource(sceneFXML));
		Scene scene = new Scene(newScreenRoot);
		scene.getStylesheets()
				.add(controller.getClass().getResource(styleSheeString).toExternalForm());

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(scene);
		window.centerOnScreen();
		window.show();
		return loader;
	}

	public static FXMLLoader inOldWindow(String sceneFXML, ActionEvent event, Object controller)
			throws IOException {
		FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(sceneFXML));
		Parent newScreenRoot = loader.load();
		//Parent newScreenRoot = FXMLLoader.load(controller.getClass().getResource(sceneFXML));
		Scene scene = new Scene(newScreenRoot);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(scene);
		window.centerOnScreen();
		window.show();
		return loader;
	}

	public static void inNewWindow(String sceneFXML, String styleSheeString, String title,
			Object controller) throws IOException {
		Parent newScreenRoot = FXMLLoader.load(controller.getClass().getResource(sceneFXML));
		Scene scene = new Scene(newScreenRoot);
		scene.getStylesheets()
				.add(controller.getClass().getResource(styleSheeString).toExternalForm());

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle(title);
		window.centerOnScreen();
		window.show();
	}

}
