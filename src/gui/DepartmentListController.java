package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import br.com.trainning.model.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DepartmentListController implements Initializable {
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColummId;

	@FXML
	private TableColumn<Department, String> tableColummName;

	@FXML
	private Button btNew;

	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColummId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColummName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		/* Acompanhar a janela toda */
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
		
	}

}
