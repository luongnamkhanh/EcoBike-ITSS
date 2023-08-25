package application.entity;

import javafx.scene.Scene;

public class SceneControllerPair{
	public Object controller;
	public Scene scene;
	
	public SceneControllerPair(Object controller, Scene scene) {
		this.controller = controller;
		this.scene = scene;
	}
}