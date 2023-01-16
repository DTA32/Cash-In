/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cashin_oop;

/**
 *
 * @author bandu
 */
public class Cashin_oop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login lg = new Login();
	lg.setVisible(true);
	lg.pack();
	lg.setLocationRelativeTo(null);
	lg.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
    }
    
}
