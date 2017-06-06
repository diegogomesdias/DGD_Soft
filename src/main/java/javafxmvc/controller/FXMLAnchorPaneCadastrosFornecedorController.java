package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmvc.model.dao.FornecedorDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Fornecedor;

public class FXMLAnchorPaneCadastrosFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tableViewFornecedor;
    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorNome;
    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorCNPJ;
    @FXML
    private Label FornecedorNome;
    @FXML
    private Label FornecedorCNPJ;
    
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
            //labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
            FornecedorNome.setText(fornecedor.getFantasia());
            FornecedorCNPJ.setText(String.valueOf(fornecedor.getCnpj()));
            //ClienteRG.setText(cliente.getRg());
            //ClienteEnd.setText(cliente.getEnd());
            //boxCidade.set
            //ClienteBairro.setText(cliente.getBairro());
            //boxUF
            //ClienteTelefone.setText(cliente.getTelefone());
            //IMAGEM
            //boxSexo
            //boxNacionalidade
            //CEP Ver se vai utilizar
            //ClienteNasc.setText(String.valueOf(cliente.getNasc()));
            //boxEstCivil
            //ClienteCelular.setText(cliente.getCelular());
            //ClienteEmail.setText(cliente.getEmail());
            //ClienteNumero.setText(String.valueOf(cliente.getNumeroEnd()));
            //ClienteComp.setText(cliente.getComplemento());
        }else{
            //labelClienteCodigo.setText("");
            //ClienteNome.setText("");
            //ClienteCPF.setText("");
            //ClienteTelefone.setText("");
        }
    }
    
}
