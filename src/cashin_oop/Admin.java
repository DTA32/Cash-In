/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cashin_oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DTA32
 */
public class Admin  extends User{
    
    public static JTable retrieveTabelTrx(){
        JTable tab = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        
        String[] namaKolom = {"ID Transaksi", "Tanggal", "Total"};
        model.setColumnIdentifiers(namaKolom);
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM transaksi"); 
           while(rs.next()){
            String idtrx = rs.getString("id_transaksi");
            java.sql.Date tanggal = rs.getDate("tanggal");
            int total = rs.getInt("total");
            model.addRow(new Object[]{idtrx, tanggal, total});
            }
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        tab.setModel(model);
        return tab;
    }
    
    public static JTable retrieveTabelUser(){
        JTable tab = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        
        String[] namaKolom = {"ID","Username", "Password", "Tipe"};
        model.setColumnIdentifiers(namaKolom);
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM user"); 
           while(rs.next()){
            String id_user = rs.getString("id_user");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int tipe = rs.getInt("tipe");
            String usertype = "";
            switch(tipe){
                case 1 -> usertype = usertype.concat("Admin");
                case 2 -> usertype = usertype.concat("Supervisor");
                case 3 -> usertype = usertype.concat("Kasir");
            }
            model.addRow(new Object[]{id_user, username, password, usertype});
           }
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        tab.setModel(model);
        return tab;
    }
    
    public static String InsertBarang(int IDB, String Nama, String Kategory, int Harga, int Stok){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String input = "INSERT INTO barang VALUES (" + IDB + ", '" + Nama + "', '" + Kategory + "', " + Harga + ", " + Stok + ")";
           stat.executeUpdate(input);
        } catch (SQLException se){
            return se.getMessage();
           }
        return "success";
    }
    
    public static void UpdateBarang(String ID, String Nama, String Kategori, int Harga, int Stok){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
              Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
              Statement stat = conn.createStatement();
              String sql = "UPDATE barang SET nama_barang = '" + Nama + "', kategori = '" + Kategori + "', harga = " + Harga + ", stok = " + Stok + " WHERE id_produk = " + ID;
              stat.executeUpdate(sql);
          } catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }
    
    public static void deleteBarang(int IDBarang){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String sql = "DELETE FROM barang WHERE id_produk = " + IDBarang;
           stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
           }
    }

    public static JTable retrieveTabelTrxDetail(int id){
        JTable tab = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }

        String[] namaKolom = {"ID Barang", "Nama", "Harga", "Kuantitas", "Subtotal"};
        model.setColumnIdentifiers(namaKolom);
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM transaksi_detail WHERE id_transaksi = " + id);
           while(rs.next()){
            String idbarang = rs.getString("id_barang");
            String nama_barang = rs.getString("nama");
            int harga = rs.getInt("harga");
            int kuantitas = rs.getInt("kuantitas");
            int subtotal = rs.getInt("subtotal");
            model.addRow(new Object[]{idbarang, nama_barang, harga, kuantitas, subtotal});
            }
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        tab.setModel(model);
        return tab;
    }
    
    public static Boolean insertUser(String uname, String pass, Integer tipe){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String input = "INSERT INTO user VALUES (0,'" + uname + "', '" + pass + "', " + tipe + ")";
            System.out.println(input);
           stat.executeUpdate(input);
           return true;
        } catch (SQLException se){
            System.out.println(se.getMessage());
           }
        return false;
    }
    
    public static void deleteUser(Integer id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String sql = "DELETE FROM user WHERE id_user = '" + id + "'";
           stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
           }
    }
    
    public static void updateUser(Integer id, String username, String password, Integer tipe){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
              Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
              Statement stat = conn.createStatement();
              String sql = "UPDATE user SET username = '" + username + "', password = '" + password + "', tipe = " + tipe + " WHERE id_user = " + id;
              stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
