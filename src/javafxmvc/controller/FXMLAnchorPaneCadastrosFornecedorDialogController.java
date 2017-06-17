
package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.dao.CidEstDao;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.CidadeEstado;
import javafxmvc.model.domain.Fornecedor;

public class FXMLAnchorPaneCadastrosFornecedorDialogController implements Initializable {

    @FXML private TextField FornecedorNomeFantasia;
    @FXML private TextField FornecedorRazaoSocial;
    @FXML private TextField FornecedorCnpj;
    @FXML private TextField FornecedorInscSocial;
    @FXML private TextField FornecedorEndereco;
    @FXML private TextField FornecedorNumero;
    @FXML private TextField FornecedorBairro;
    @FXML private TextField FornecedorEmail;
    @FXML private TextField FornecedorCep;
    @FXML private TextField FornecedorTelefone;
    @FXML private ComboBox boxTipoEmpresa;
    @FXML private ComboBox boxUF;
    @FXML private ComboBox boxCidade;
            
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Fornecedor fornecedor;
    
    //OBJETOS UTILIZADOS
    private String uf;
    private String cidade;
        
    private List<CidadeEstado> listaCidEst;
    private ObservableList<CidadeEstado> observableCidEst;
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CidEstDao cidestDao = new CidEstDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cidestDao.setConnection(connection);
        
        //carregarEstado();
        carregaComboBoxEstados();
        carregaCidades();
    }    
    
    //CONVERTE UMA STRING EM FORMATO DATE
    public static java.sql.Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
    
     //Carregar paises na ComboBox
    /*
    public void CarregaPais(){
        List<String> listaPais = cidestDao.listaPais();
        listaPais.forEach((nomePais) -> {
            boxNacionalidade.getItems().add(nomePais);
         });
    }
    
    //CARREGA OS ESTADOS E OS ADD EM UMA COMBOBOX    List<String> listaEstado = cidestDao.listaEstados();
        
    public void carregarEstado(){
        List<String> listaEstado = cidestDao.listaEstados();
        listaEstado.forEach((nomeEstado) -> {
            boxUF.getItems().add(nomeEstado);
        });
    }
    */
    public void carregaComboBoxEstados(){
        listaCidEst = cidestDao.listarTodosEtados();
        observableCidEst = FXCollections.observableArrayList(listaCidEst);
        boxUF.setItems(observableCidEst);
    }
    
    public void carregaCidades(){
        List<String> listaCidade = cidestDao.listaCidades();
        listaCidade.forEach((nomeCidade) -> {
            boxCidade.getItems().add(nomeCidade);
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
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    public Fornecedor getCliente() {
        return fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        
        this.FornecedorBairro.setText(fornecedor.getBairro());
        this.FornecedorCep.setText(fornecedor.getCep());
        this.FornecedorCnpj.setText(String.valueOf(fornecedor.getCnpj()));
        this.FornecedorEmail.setText(fornecedor.getEmail());
        this.FornecedorEndereco.setText(fornecedor.getEndereco());
        this.FornecedorInscSocial.setText(String.valueOf(fornecedor.getIncsocial()));
        this.FornecedorNomeFantasia.setText(fornecedor.getFantasia());
        this.FornecedorNumero.setText(String.valueOf(fornecedor.getNumero()));
        this.FornecedorRazaoSocial.setText(fornecedor.getRsocial());
        this.FornecedorTelefone.setText(fornecedor.getTelefone());
        this.boxUF.valueProperty().set(fornecedor.getUf());
        this.boxCidade.valueProperty().set(fornecedor.getCidade());
        this.boxTipoEmpresa.valueProperty().set(fornecedor.getTipoempresa());
    }
    
    @FXML
    public void handleBtnConfirmar() throws Exception{
        fornecedor.setBairro(FornecedorBairro.getText());
        fornecedor.setCep(FornecedorCep.getText());
        fornecedor.setCnpj(Integer.parseInt(FornecedorCnpj.getText()));
        fornecedor.setEmail(FornecedorEmail.getText());
        fornecedor.setEndereco(FornecedorEndereco.getText());
        fornecedor.setIncsocial(Integer.parseInt(FornecedorInscSocial.getText()));
        fornecedor.setFantasia(FornecedorNomeFantasia.getText());
        fornecedor.setNumero(Integer.parseInt(FornecedorNumero.getText()));
        fornecedor.setRsocial(FornecedorRazaoSocial.getText());
        fornecedor.setTelefone(FornecedorTelefone.getText());
        fornecedor.setUf(String.valueOf(boxUF.valueProperty().get()));
        fornecedor.setCidade(String.valueOf(boxCidade.valueProperty().get()));
        fornecedor.setTipoempresa(Integer.parseInt(String.valueOf(boxTipoEmpresa.valueProperty().get())));
        
        btnConfirmarClicked = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleBtnCancelar(){
        dialogStage.close();
    }
    
}
