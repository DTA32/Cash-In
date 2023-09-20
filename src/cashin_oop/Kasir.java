/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cashin_oop;

import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author DTA32
 */
public class Kasir extends User {
    public static void insertTrx(TableModel tableModel){
        // create new transaksi
        Date tanggalDate = new Date();
        String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(tanggalDate);
        int total = 0;
        for(int i = 0; i < tableModel.getRowCount(); i++){
            int subtotal = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            total += subtotal;
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
            Statement stat = conn.createStatement();
            String input = "INSERT INTO transaksi VALUES (0,'" + tanggal + "', " + total + ")";
            stat.executeUpdate(input);
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        int id_transaksi = 0;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
            Statement stat = conn.createStatement();
            String sql = "SELECT MAX(id_transaksi) FROM transaksi";
            ResultSet rs = stat.executeQuery(sql);
            rs.next();
            id_transaksi = rs.getInt(1);
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        for(int i = 0; i < tableModel.getRowCount(); i++){
            int id_barang = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            String nama = tableModel.getValueAt(i, 1).toString();
            int harga = Integer.parseInt(tableModel.getValueAt(i, 2).toString());
            int kuantitas = Integer.parseInt(tableModel.getValueAt(i, 3).toString());
            int subtotal = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
                Statement stat = conn.createStatement();
                String input = "INSERT INTO transaksi_detail VALUES ("+ (id_transaksi * 10 + (i+1)) +", " + id_transaksi + ", " + id_barang + ", '" + nama + "', " + harga + ", " + kuantitas + ", " + subtotal + ")";
                stat.executeUpdate(input);
            } catch (SQLException se){
                System.out.println(se.getMessage());
            }
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
                Statement stat = conn.createStatement();
                String input = "UPDATE barang SET stok = stok - " + kuantitas + " WHERE id_produk = " + id_barang;
                stat.executeUpdate(input);
            } catch (SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }
}
