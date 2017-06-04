
package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrosCategorias;
    @FXML
    private MenuItem menuItemCadastrosClientes;
    @FXML
    private MenuItem menuItemCadastrosFornecedores;
    @FXML
    private MenuItem menuItemCadastrosProdutos;
    @FXML        
    private MenuItem menuItemProcessosVendas;
    @FXML
    private MenuItem menuItemGraficosVendaMes;
    @FXML
    private MenuItem menuItemRelatoriosQtdProduto;
    @FXML
    private AnchorPane anchorPane;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    public void handleMenuItemCadastrosClientes() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemCadastrosFornecedor() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosFornecedor.fxml"));
        anchorPane.getChildren().setAll(a);
    }
}
