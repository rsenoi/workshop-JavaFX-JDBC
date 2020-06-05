package gui;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.com.trainning.dao.DataChangeListener;
import br.com.trainning.dao.DepartmentDAO;
import br.com.trainning.model.Department;
import br.com.trainning.util.Conexao;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements  Initializable {
	
	private Department entity;

	Connection con = Conexao.abrirConexao();

	private DepartmentDAO service = new DepartmentDAO(con);
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;
	
	public void setDerpartment(Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentDAO(DepartmentDAO service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		if(service == null) {
			throw new IllegalStateException("Service was null");
			
		}
		
		entity = getFormData();
		service.saveOrUpdate(entity);
		
		notifyDataChangeListener();

		Conexao.fecharConexao(con);

		Utils.currentStage(event).close();
		

	}

	private void notifyDataChangeListener() {

		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		
	}
	

}
