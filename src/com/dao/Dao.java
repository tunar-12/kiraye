package com.dao;

import com.model.Users;
import com.model.Payments;
import java.util.ArrayList;
import java.util.List;

public interface Dao {

    public List<Payments> getAllPayments(String selectedUser);

    public ArrayList<String> getAllUsers();

    public List<Users> getAllUsers1();

    public boolean addUser(String newUser);

    public boolean updateBookByStatus(int selectedId);
    
    public boolean addPayment(Payments newBook) ;

    public Integer countMonthes(String selectedUser);
    
    public Integer countMoney(String selectedUser);
    
     public boolean deleteBook(int selectedId) ;
}
