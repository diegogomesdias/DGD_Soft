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
    private ComboBox boxUF;
    @FXML
    private ComboBox boxCidade;
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
        carregarEstado();
        
        //listem acionado diante de qualquer alterações na seleção de itens da Tableview
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldValue,newValue) -> selecionarItemTableViewClientes(newValue));
        
    }    
    
    //CARREGA OS ESTADOS E OS ADD EM UMA COMBOBOX    
    public void carregarEstado(){
        List<String> listaEstado = cidestDao.listaEstados();
        listaEstado.forEach((nomeEstado) -> {
            boxUF.getItems().add(nomeEstado);
        });
    }
    
    //TORNA OS NOMES COMPLETOS EM SIGLAS
    private String EstadoNomeParaSigla(){
        String estado = null;
        if("Acre".equals(boxUF.valueProperty().get()) ) estado = "AC";
        if("Alagoas".equals(boxUF.valueProperty().get()) ) estado = "AL";
        if("Amazonas".equals(boxUF.valueProperty().get()) ) estado = "AM";
        if("Amapá".equals(boxUF.valueProperty().get()) ) estado = "AP";
        if("Bahia".equals(boxUF.valueProperty().get()) ) estado = "BA";
        if("Ceará".equals(boxUF.valueProperty().get()) ) estado = "CE";
        if("Distrito Federal".equals(boxUF.valueProperty().get()) ) estado = "DF";
        if("Espírito Santo".equals(boxUF.valueProperty().get()) ) estado = "ES";
        if("Goiás".equals(boxUF.valueProperty().get()) ) estado = "GO";
        if("Maranhão".equals(boxUF.valueProperty().get()) ) estado = "MA";
        if("Minas Gerais".equals(boxUF.valueProperty().get()) ) estado = "MG";
        if("Mato Grosso do Sul".equals(boxUF.valueProperty().get()) ) estado = "MS";
        if("Mato Grosso".equals(boxUF.valueProperty().get()) ) estado = "MT";
        if("Pará".equals(boxUF.valueProperty().get()) ) estado = "PA";
        if("Paraíba".equals(boxUF.valueProperty().get()) ) estado = "PB";
        if("Pernambuco".equals(boxUF.valueProperty().get()) ) estado = "PE";
        if("Piauí".equals(boxUF.valueProperty().get()) ) estado = "PI";
        if("Paraná".equals(boxUF.valueProperty().get()) ) estado = "PR";
        if("Rio de Janeiro".equals(boxUF.valueProperty().get()) ) estado = "RJ";
        if("Rio Grande do Norte".equals(boxUF.valueProperty().get()) ) estado = "RN";
        if("Rondônia".equals(boxUF.valueProperty().get()) ) estado = "RO";
        if("Roraima".equals(boxUF.valueProperty().get()) ) estado = "RR";
        if("Rio Grande do Sul".equals(boxUF.valueProperty().get()) ) estado = "RS";
        if("Santa Catarina".equals(boxUF.valueProperty().get()) ) estado = "SC";
        if("Sergipe".equals(boxUF.valueProperty().get()) ) estado = "SE";
        if("São Paulo".equals(boxUF.valueProperty().get()) ) estado = "SP";
        if("Tocantins".equals(boxUF.valueProperty().get()) ) estado = "TO";
        return estado;
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
            //boxCidade.set
            ClienteBairro.setText(cliente.getBairro());
            //boxUF
            ClienteTelefone.setText(cliente.getTelefone());
            //IMAGEM
            //boxSexo
            //boxNacionalidade
            //CEP Ver se vai utilizar
            //ClienteNasc.setText(String.valueOf(cliente.getNasc()));
            //boxEstCivil
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
        loader.setLocation(FXMLAnchorPaneCadastrosClientesDialogController.class.getResource("/view/FXMLAnchorPaneCadastrosClientesDialog.fxml"));
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
