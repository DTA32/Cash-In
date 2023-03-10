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
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();     
        } catch (Exception e){
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
        }
        tab.setModel(model);
        return tab;
    }
    
    public static JTable retrieveTabelUser(){
        JTable tab = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();     
        } catch (Exception e){
            System.out.println(e);
        }
        
        String[] namaKolom = {"Username", "Password", "Tipe"};
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
                case 1:
                    usertype = usertype.concat("Admin");
                    break;
                case 2:
                    usertype = usertype.concat("Supervisor");
                    break;
                case 3:
                    usertype = usertype.concat("Kasir");
                    break;
            }
            model.addRow(new Object[]{username, password, usertype});
           }
        } catch (SQLException se){
        }
        tab.setModel(model);
        return tab;
    }
    
    public static void InsertBarang(String IDB, String Nama, String Kategory, int Harga, int Stok){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();     
        } catch (Exception e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String input = "INSERT INTO barang VALUES (" + IDB + ", '" + Nama + "', '" + Kategory + "', " + Harga + ", " + Stok + ")";
           stat.executeUpdate(input);
        } catch (SQLException se){
           }
    }
    public static void deleteBarang(int IDBarang){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();     
        } catch (Exception e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String sql = "DELETE FROM barang WHERE id_produk = " + IDBarang;
           stat.executeUpdate(sql);
        } catch (SQLException se){
           }
    }
}
