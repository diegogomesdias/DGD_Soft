package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.CidEstDao;
import javafxmvc.model.dao.ClienteDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.CidadeEstado;
import javafxmvc.model.domain.Cliente;

public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCpf;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Label ClienteNome;
    @FXML
    private Label boxSexo;
    @FXML
    private Label boxNacionalidade;
    @FXML
    private Label boxEstCivil;
    @FXML
    private Label ClienteCPF;
    @FXML
    private Label ClienteRG;
    @FXML
    private Label ClienteNasc;
    @FXML
    private Label ClienteEnd;
    @FXML
    private Label ClienteNumero;
    @FXML
    private Label ClienteComp;
    @FXML
    private Label ClienteBairro;
    @FXML
    private Label ClienteUF;
    @FXML
    private Label ClienteCidade;
    @FXML
    private Label ClienteTelefone;
    @FXML
    private Label ClienteCelular;
    @FXML
    private Label ClienteEmail;
    
    @FXML
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDao = new ClienteDAO();
    private final CidEstDao cidestDao = new CidEstDao();
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDao.setConnection(connection); //conexao setada no cliente dao
        cidestDao.setConnection(connection);
        carregarTableVielCliente();
        
        
        //listem acionado diante de qualquer alterações na seleção de itens da Tableview
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldValue,newValue) -> selecionarItemTableViewClientes(newValue));
        
    }    
    
    public void carregarTableVielCliente(){
        //configura as colunas para exibir o nome e cpf na tabela (TableView)
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        //chama o metodo listar
        listClientes = clienteDao.listar();
        //converte um observableList para um arraylist
        observableListClientes = FXCollections.observableArrayList(listClientes);
        //passa o observablelist para a tableview
        tableViewClientes.setItems(observableListClientes);
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if(cliente != null){
            labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
            ClienteNome.setText(cliente.getNome());
            ClienteCPF.setText(cliente.getCpf());
            ClienteRG.setText(cliente.getRg());
            ClienteEnd.setText(cliente.getEnd());
            ClienteUF.setText(cliente.getUf());
            ClienteBairro.setText(cliente.getBairro());
            ClienteCidade.setText(cliente.getCidade());
            ClienteTelefone.setText(cliente.getTelefone());
            //IMAGEM
            boxSexo.setText(cliente.getSexo());
            boxNacionalidade.setText(cliente.getNacionalidade());
            //CEP Ver se vai utilizar
            //ClienteNasc.setText(String.valueOf(cliente.getNasc()));
            boxEstCivil.setText(cliente.getEstCivil());
            //ClienteCelular.setText(cliente.getCelular());
            //ClienteEmail.setText(cliente.getEmail());
            ClienteNumero.setText(String.valueOf(cliente.getNumeroEnd()));
            //ClienteComp.setText(cliente.getComplemento());
        }else{
            labelClienteCodigo.setText("");
            ClienteNome.setText("");
            ClienteCPF.setText("");
            ClienteTelefone.setText("");
        }
    }
    
    @FXML
    public void handleBtnInserir() throws IOException{
        Cliente cliente = new Cliente();
        boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        
        if(btnConfirmarClicked){
            clienteDao.inserir(cliente);
            carregarTableVielCliente();
        }
    }
    
    @FXML
    public void handleBtnAlterar() throws IOException{
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
            if(btnConfirmarClicked){
                clienteDao.alterar(cliente);
                carregarTableVielCliente();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtnExcluir()throws IOException{
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            clienteDao.remover(cliente);
            carregarTableVielCliente();
        }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Por favor, escolha um cliente na Tabela");
           alert.show(); 
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosClientesDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //cria um estagio de dialogo(Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastros de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //setando o cliente no controller
        FXMLAnchorPaneCadastrosClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        
        //mostra o dialog  e espera ate que o usuario o feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();
    }
}
