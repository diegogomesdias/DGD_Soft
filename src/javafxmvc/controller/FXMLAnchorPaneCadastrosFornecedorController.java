package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.FornecedorDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Cliente;
import javafxmvc.model.domain.Fornecedor;

public class FXMLAnchorPaneCadastrosFornecedorController implements Initializable {

    @FXML private TableView<Fornecedor> tableViewFornecedor;
    @FXML private TableColumn<Fornecedor, String> tableColumnFornecedorNome;
    @FXML private TableColumn<Fornecedor, String> tableColumnFornecedorCNPJ;
    @FXML private Label FornecedorNome;
    @FXML private Label FornecedorCNPJ;
    @FXML private Label FornecedorCodigo;
    @FXML private Label FornecedorRSocial;
    @FXML private Label FornecedorEndereco;
    @FXML private Label FornecedorBairro;
    @FXML private Label FornecedorEmail;
    @FXML private Label FornecedorTipoEmpresa;
    @FXML private Label FornecedorNumero;
    @FXML private Label FornecedorUF;
    @FXML private Label FornecedorCep;
    @FXML private Label FornecedorInscSocial;
    @FXML private Label FornecedorCidade;
    @FXML private Label FornecedorTelefone;
    
    @FXML
    private List<Fornecedor> listFornecedor;
    private ObservableList<Fornecedor> observableListFornecedor;
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
        carregarTableVielFornecedor();
        tableViewFornecedor.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldValue,newValue) -> selecionarItemTableViewFornecedor(newValue));
        
    }    
    
    public void carregarTableVielFornecedor(){
        //configura as colunas para exibir o nome e cpf na tabela (TableView)
        tableColumnFornecedorNome.setCellValueFactory(new PropertyValueFactory<>("fantasia"));
        tableColumnFornecedorCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        //chama o metodo listar
        listFornecedor = fornecedorDAO.listar();
        //converte um observableList para um arraylist
        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);
        //passa o observablelist para a tableview
        tableViewFornecedor.setItems(observableListFornecedor);
    }
    
    public void selecionarItemTableViewFornecedor(Fornecedor fornecedor){
        if(fornecedor != null){
            FornecedorCodigo.setText(String.valueOf(fornecedor.getId()));
            FornecedorNome.setText(fornecedor.getFantasia());
            FornecedorCNPJ.setText(String.valueOf(fornecedor.getCnpj()));
            FornecedorRSocial.setText(fornecedor.getRsocial());
            FornecedorEndereco.setText(fornecedor.getEndereco());
            FornecedorCidade.setText(fornecedor.getCidade());
            FornecedorBairro.setText(fornecedor.getBairro());
            FornecedorUF.setText(fornecedor.getUf());
            FornecedorTelefone.setText(fornecedor.getTelefone());
            FornecedorInscSocial.setText(String.valueOf(fornecedor.getIncsocial()));
            FornecedorTipoEmpresa.setText(String.valueOf(fornecedor.getTipoempresa()));
            //IMAGEM
            FornecedorEmail.setText(fornecedor.getEmail());
            FornecedorNumero.setText(String.valueOf(fornecedor.getNumero()));
        }else{
            FornecedorCodigo.setText("");
            FornecedorNome.setText("");
            FornecedorCNPJ.setText("");
            FornecedorRSocial.setText("");
            FornecedorEndereco.setText("");
            FornecedorCidade.setText("");
            FornecedorBairro.setText("");
            FornecedorUF.setText("");
            FornecedorTelefone.setText("");
            FornecedorInscSocial.setText("");
            FornecedorTipoEmpresa.setText("");
            //IMAGEM
            FornecedorEmail.setText("");
            FornecedorNumero.setText("");
        }
    }
    
    @FXML
    public void handleBtnInserir() throws IOException{
        Fornecedor fornecedor = new Fornecedor();
        boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosFornecedorDialog(fornecedor);
        
        if(btnConfirmarClicked){
            fornecedorDAO.inserir(fornecedor);
            carregarTableVielFornecedor();
        }
    }
    
    @FXML
    public void handleBtnAlterar() throws IOException{
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if(fornecedor != null){
            boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosFornecedorDialog(fornecedor);
            if(btnConfirmarClicked){
                fornecedorDAO.alterar(fornecedor);
                carregarTableVielFornecedor();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtnExcluir()throws IOException{
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if(fornecedor != null){
            fornecedorDAO.remover(fornecedor);
            carregarTableVielFornecedor();
        }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Por favor, escolha um cliente na Tabela");
           alert.show(); 
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosFornecedorDialog(Fornecedor fornecedor) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosFornecedorDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosFornecedorDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //cria um estagio de dialogo(Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastros de Fornecedores");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //setando o Fornecedor no controller
        FXMLAnchorPaneCadastrosFornecedorDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFornecedor(fornecedor);
        
        //mostra o dialog  e espera ate que o usuario o feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();
    }
    
}
