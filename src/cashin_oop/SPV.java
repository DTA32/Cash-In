/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cashin_oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.TableModel;

/**
 *
 * @author DTA32
 */
public class SPV extends Management{
    public static void deleteTrx(int id){
        // delete all transaksi_detail related to transaksi
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");     
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String sql = "DELETE FROM transaksi_detail WHERE id_transaksi = " + id;
           stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
           }
        // delete the transaksi
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
           Statement stat = conn.createStatement();
           String sql = "DELETE FROM transaksi WHERE id_transaksi = " + id;
           stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
           }
    }
    public static void updateTrx(int id, TableModel model){
        int total = 0;
        for(int i = 0; i < model.getRowCount(); i++){
            // masih belum safe
            int harga = Integer.parseInt(model.getValueAt(i, 2).toString());
            int kuantitas = Integer.parseInt(model.getValueAt(i, 3).toString());
            int subtotal = harga * kuantitas;
            total += subtotal;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e){
                System.out.println(e);
            }
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
                Statement stat = conn.createStatement();
                String sql = "UPDATE transaksi_detail SET kuantitas = " + kuantitas + ", subtotal = " + subtotal + " WHERE id_transaksi = " + id + " AND id_barang = " + model.getValueAt(i, 0);
                stat.executeUpdate(sql);
            } catch (SQLException se){
                System.out.println(se.getMessage());
            }
        }
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/cashin", "vscode", "root");
            Statement stat = conn.createStatement();
            String sql = "UPDATE transaksi SET total = " + total + " WHERE id_transaksi = " + id;
            stat.executeUpdate(sql);
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
