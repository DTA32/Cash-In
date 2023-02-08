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
public class User {
    public static JTable retrieveTabelBarang(){
        JTable tab = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();     
        } catch (Exception e){
            System.out.println(e);
        }
        
        String[] namaKolom = {"ID Barang", "Nama", "Kategori", "Harga", "Stok"};
        model.setColumnIdentifiers(namaKolom);
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM barang"); 
           while(rs.next()){
            String idbarang = rs.getString("id_produk");
            String nama_barang = rs.getString("nama_barang");
            String kategori = rs.getString("kategori");
            int harga = rs.getInt("harga");
            int stok = rs.getInt("stok");
            model.addRow(new Object[]{idbarang, nama_barang, kategori, harga, stok});
            }
        } catch (SQLException se){
        }
        tab.setModel(model);
        return tab;
    }
}
