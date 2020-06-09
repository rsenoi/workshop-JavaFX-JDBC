package gui;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import br.com.trainning.dao.DataChangeListener;
import br.com.trainning.dao.SellerDAO;
import br.com.trainning.model.Seller;
import br.com.trainning.util.Conexao;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.exception.ValidationException;

public class SellerFormController implements  Initializable {
	
	private Seller entity;

	Connection con = Conexao.abrirConexao();

	private SellerDAO service = new SellerDAO(con);
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;

	@FXML
	private DatePicker dpBirthDate;

	@FXML
	private TextField txtBaseSalary;

	@FXML
	private TextField txtEmail;

	@FXML
	private Label labelErrorName;

	@FXML
	private Label labelErrorEmail;

	@FXML
	private Label labelErrorBirthDate;

	@FXML
	private Label labelErrorBaseSalary;
	
	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;
	
	public void setSeller(Seller entity) {
		this.entity = entity;
	}
	
	public void setSellerDAO(SellerDAO service) {
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
		
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			
			notifyDataChangeListener();
	
			Utils.currentStage(event).close();
		}
		catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		

	}

	private void notifyDataChangeListener() {

		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
			
		}
		
	}

	private Seller getFormData() {
		Seller obj = new Seller();
		ValidationException exception = new ValidationException("Validation error");
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		if(txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "fild can't be empty");
		}
		obj.setName(txtName.getText());
		
		if(exception.getErrors().size()>0) {
			throw exception;
		}
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
		Constraints.setTextFieldMaxLength(txtName, 70);
		Constraints.setTextFieldDouble(txtBaseSalary);
		Constraints.setTextFieldMaxLength(txtEmail, 60);
		Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		txtEmail.setText(entity.getEmail());
		Locale.setDefault(Locale.US);
		txtBaseSalary.setText(String.format("%.2f",entity.getBaseSalary()));
		if(entity.getBirthdate()!=null) {
			dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthdate().toInstant(), ZoneId.systemDefault()));
		}
	}
	
	private void setErrorMessages(Map<String, String> error) {
		Set<String> fields = error.keySet();
		
		if(fields.contains("name")) {
			labelErrorName.setText(error.get("name"));
		}
		
	}
	


}
