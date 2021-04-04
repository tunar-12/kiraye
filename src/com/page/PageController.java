package com.page;

import com.dao.DaoImpl;
import com.model.Payments;
import com.model.Users;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PageController implements Initializable {

    int selectedId = 0;

    String selectedUser = "";
    DaoImpl dao = new DaoImpl();
    ArrayList<String> usersArr = new ArrayList<String>();
    TableColumn<Users, String> users;
    @FXML
    private TableView<Payments> tableView;
    @FXML
    private TableColumn<Payments, Integer> id;
    @FXML
    private TableColumn<Payments, String> day;
    @FXML
    private TableColumn<Payments, String> month;
    @FXML
    private TableColumn<Payments, String> year;
    @FXML
    private TableColumn<Payments, String> money;
    @FXML
    private TableColumn<Payments, String> user;
    @FXML
    private Label monthLbl;
    @FXML
    private Label moneyLbl;
    @FXML
    private TextField dayTf;
    @FXML
    private ComboBox<String> monthCb;
    @FXML
    private TextField moneyTf;
    @FXML
    private ComboBox<String> userCb;
    @FXML
    private Button addBtnNew;
    @FXML
    private Label warningLbl;
    @FXML
    private Button addUser;
    private ComboBox<String> chooseFilteredUserCb;
    @FXML
    private TableColumn<Payments, String> status;
    @FXML
    private TextField yearTf;
    @FXML
    private Button pay;
    @FXML
    private TableView<Users> tableView2;
    @FXML
    private Button filterUsers;
    @FXML
    private TableColumn<?, ?> userRow;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button borrowBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setVisible(false);
        loadMonth();
        for (int i = 0; i < dao.getAllUsers().size(); i++) {
            usersArr.add(dao.getAllUsers().get(i));
        }
        loadUser();
        loadColumnUsers();
        loadRowsUsers();
    }

    private void loadMonth() {
        monthCb.getItems().add("Yanvar");
        monthCb.getItems().add("Fevral");
        monthCb.getItems().add("Mart");
        monthCb.getItems().add("Aprel");
        monthCb.getItems().add("May");
        monthCb.getItems().add("Iyun");
        monthCb.getItems().add("Iyul");
        monthCb.getItems().add("Avqust");
        monthCb.getItems().add("Sentyabr");
        monthCb.getItems().add("Oktyabr");
        monthCb.getItems().add("Dekabr");
    }

    public void refresh() {
        tableView.getItems().clear();
        tableView.getItems().addAll(dao.getAllPayments(selectedUser));
        int x = dao.countMonthes(selectedUser);
        String th = x + "";
        monthLbl.setText(th + " ay,");
        int y = dao.countMoney(selectedUser);
        String h = y + "";
        moneyLbl.setText(h + " manat");
    }

    private void loadUser() {
        for (int i = 0; i < usersArr.size(); i++) {
            userCb.getItems().add(usersArr.get(i));
        }
    }

    @FXML
    private void addUserOnAction(ActionEvent event) {
        String newUser = JOptionPane.showInputDialog(null, "Ustanin adi?");
        if (newUser.equalsIgnoreCase("")) {
            warningLbl.setText("Hec ne daxil etmediniz!");
        } else {
            if (dao.addUser(newUser)) {
                warningLbl.setText("Yeni Usta ugurla elave olundu!!");

            } else {
                warningLbl.setText("Elave oluna bilmedi!!");
            }
        }
    }

    @FXML
    private void payOnAction(ActionEvent event) {
        boolean isUpdated = dao.updateBookByStatus(selectedId);
        if (isUpdated) {
            refresh();
            warningLbl.setText("Status deyisdi!");
        } else {
            warningLbl.setText("Status deyisilemedi!");
        }

    }

    @FXML
    private void filterUsersOnAction(ActionEvent event) {
        tableView.setVisible(true);
        loadColumn();
        loadRows();
        int x = dao.countMonthes(selectedUser);
        String th = x + "";
        monthLbl.setText(th + " ay,");
        int y = dao.countMoney(selectedUser);
        String h = y + "";
        moneyLbl.setText(h + " manat");
    }

    @FXML
    private void tableView2OnMousePressed(MouseEvent event) {
        Users book = tableView2.getSelectionModel().getSelectedItem();
        selectedUser = book.getUser();
    }

    @FXML
    private void tableViewOnMousePressed(MouseEvent event) {
        Payments book = tableView.getSelectionModel().getSelectedItem();
        selectedId = book.getId();
    }

    @FXML
    private void addBtnNewOnAction(ActionEvent event) {
        warningLbl.setText("");
        if (dayTf.getText().equalsIgnoreCase("") || yearTf.getText().equalsIgnoreCase("") || moneyTf.getText().equalsIgnoreCase("")) {
            warningLbl.setText("Butun xanalari doldurun!");
        } else {
            try {
                Payments newBook = new Payments();
                newBook.setDay(dayTf.getText());
                newBook.setMonth(monthCb.getSelectionModel().getSelectedItem());
                newBook.setYear(yearTf.getText());
                newBook.setMoney(moneyTf.getText());
                newBook.setUser(userCb.getSelectionModel().getSelectedItem());
                if (dao.addPayment(newBook)) {
                    warningLbl.setText("Yeni borc elave olundu!");
                    refresh();
                } else {
                    warningLbl.setText("Yeni Borc Elave oluna bilmedi!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadColumnUsers() {
        userRow.setCellValueFactory(new PropertyValueFactory<>("user"));
    }

    private void loadRowsUsers() {
        tableView2.getItems().addAll(dao.getAllUsers1());
    }

    private void loadColumn() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        money.setCellValueFactory(new PropertyValueFactory<>("money"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadRows() {
        tableView.getItems().addAll(dao.getAllPayments(selectedUser));
    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
        if (dao.deleteBook(selectedId)) {
            refresh();
            warningLbl.setText("Silindi!");
        } else {
            warningLbl.setText("Siline bilmedi!");
        }
    }

    @FXML
    private void borrowBtnOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("List");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/borrow/borrow.fxml"));
            stage.getIcons().add(new Image("com/images/borrow.png"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
