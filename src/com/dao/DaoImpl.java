
package com.dao;

import com.model.Payments;
import com.model.Users;

import com.util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
// Connection    Baza ile elaqe
// PreparedStatement    Bazadan gelen sql i icraya hazirlayir
// ResultSet     bazadan melumat getirir
public class DaoImpl implements Dao{
String name;
    @Override
    public List<Payments> getAllPayments(String selectedUser) {
      
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payments> allBooks = new ArrayList<Payments>();
        String sql = "select id,day,month,year,money,user,status from kiraye.payments\n"
                + "where user='" + selectedUser + "'";;
        c = DBHelper.getConnection();
        if (c != null) {
            try {

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payments task1 = new Payments();
                    task1.setId(rs.getInt("id"));
                    task1.setDay(rs.getString("day"));
                    task1.setMonth(rs.getString("month"));
                    task1.setYear(rs.getString("year"));
                        task1.setMoney(rs.getString("money") + " AZN");
                        task1.setUser(rs.getString("user"));
                            task1.setStatus(rs.getString("status"));
                    allBooks.add(task1);
                       
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return allBooks;

    }

    @Override
    public ArrayList<String> getAllUsers() {
           Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> allBooks = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>();
        String sql = "select id,user from kiraye.users";
        c = DBHelper.getConnection();
        if (c != null) {
            try {

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                   allBooks.add(rs.getString("user"));
                   
                }
               
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return allBooks;
    }

    @Override
    public boolean addUser(String newUser) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        
        String sql = "insert into kiraye.users(user)\n"
                + "values ('" + newUser + "')";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Problem inrvrebver");
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't any connection");
        }

        return result;
    }

    @Override
    public boolean updateBookByStatus(int selectedId) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update kiraye.payments\n"
                + "set status='ODENILIB' \n"
                + "where id=" + selectedId + "";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't connection!");
        }
        return result;
    }

    @Override
    public List<Users> getAllUsers1() {
        
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Users> allBooks = new ArrayList<Users>();
        String sql = "select id,user from kiraye.users";
        c = DBHelper.getConnection();
        if (c != null) {
            try {

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Users task1 = new Users();
                    task1.setId(rs.getInt("id"));
                    task1.setUser(rs.getString("user"));
                   
                    allBooks.add(task1);

                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return allBooks;
    }
@Override
 public boolean addPayment(Payments newBook) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;

String sql1 = "INSERT INTO `kiraye`.`payments` (`day`, `month`, `year`, `money`, `user`)\n" 
        + "VALUES ('" + newBook.getDay() + "', '" + newBook.getMonth() + "', '" + newBook.getYear() + "', '" + newBook.getMoney() + "', '" + newBook.getUser() + "');";        
c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql1);
                ps.execute();
          
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Problem inrvrebver");
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't any connection");
        }

        return result;
    }

    @Override
    public Integer countMonthes(String selectedUser) {
      
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int say = 0;
        String sql = "select id,day,month,year,money,user,status from kiraye.payments\n"
                + "where user='" + selectedUser + "' and status='" + "ODENILMEYIB" + "'";
        c = DBHelper.getConnection();
        if (c != null) {
            try {

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                  say++;
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return say;

    }

    @Override
    public Integer countMoney(String selectedUser) {
       
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int pul = 0;
        String sql = "select id,day,month,year,money,user,status from kiraye.payments\n"
                + "where user='" + selectedUser + "' and status='" + "ODENILMEYIB" + "'";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
        
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    
                  String u = rs.getString("money");
                  int x = Integer.parseInt(u);
                  pul+=x;
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return pul;

    }

    @Override
    public boolean deleteBook(int selectedId) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "delete from kiraye.payments\n"
                + "where id=" + selectedId + "";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't connection!");
        }
        return result;
    }

    
}
