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
public class Management extends User{
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
}
