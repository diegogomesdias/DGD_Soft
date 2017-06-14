/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.ProdutoDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Produto;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class FXMLAnchorPaneCadastrosProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoTipo;
    
    
    @FXML
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;
    
    
     //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDao = new ProdutoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void carregarTableVielProduto(){
        //configura as colunas para exibir o nome e cpf na tabela (TableView)
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnProdutoTipo.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        //chama o metodo listar
        listProdutos = produtoDao.listar();
        //converte um observableList para um arraylist
        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        //passa o observablelist para a tableview
        tableViewProdutos.setItems(observableListProdutos);
    }
    
    @FXML
    public void handleBtnInserir() throws IOException{
        Produto produto = new Produto();
        boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosProdutoDialog(produto);
        
        if(btnConfirmarClicked){
            produtoDao.inserir(produto);
            carregarTableVielProduto();
        }
    }
    
    @FXML
    public void handleBtnAlterar() throws IOException{
        Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if(produto != null){
            boolean btnConfirmarClicked = showFXMLAnchorPaneCadastrosProdutoDialog(produto);
            if(btnConfirmarClicked){
                produtoDao.alterar(produto);
                carregarTableVielProduto();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtnExcluir()throws IOException{
        Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if(produto != null){
            produtoDao.remover(produto);
            carregarTableVielProduto();
        }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Por favor, escolha um cliente na Tabela");
           alert.show(); 
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosProdutoDialog(Produto produto) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosProdutoDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosProdutoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //cria um estagio de dialogo(Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastros de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //setando o cliente no controller
        FXMLAnchorPaneCadastrosProdutoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);
        
        //mostra o dialog  e espera ate que o usuario o feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();
    }
}
