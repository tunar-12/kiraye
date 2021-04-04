
package com.borrow;

import com.dao.DaoImpl;
import com.model.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BorrowController implements Initializable {
DaoImpl dao = new DaoImpl();
String selectedUser = "";
    @FXML
    private TableView<Users> table;
    @FXML
    private TableColumn<Users, String> user;
    @FXML
    private Label month;
    @FXML
    private Label money;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadColumn();
       loadRows();
       month.setText("");
       money.setText("");
    }    
    private void loadColumn(){
          user.setCellValueFactory(new PropertyValueFactory<>("user"));
    }
    
    private void loadRows(){
         table.getItems().addAll(dao.getAllUsers1());
    }

    @FXML
    private void tableOnMousePressed(MouseEvent event) {
     Users book = table.getSelectionModel().getSelectedItem();
        selectedUser = book.getUser();
        int x = dao.countMonthes(selectedUser);
        String th = x + "";
        month.setText(th + " ay");
        int y = dao.countMoney(selectedUser);
        String h = y + "";
        money.setText(h + " manat");
    }
}
