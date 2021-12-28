import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;

public class App {
    static Connection conn;
    public static void main(String[] args) throws Exception {
        Scanner inputuser = new Scanner(System.in);
        String choice;
        boolean keadaan = true;
        String URL = "jdbc:mysql://localhost:3306/tugas";
        String user = "root";
        String password ="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, user, password);
            System.out.println("Berhasil Terkoneksi");
            penjualan pjl = new penjualan();
            while(keadaan){
                System.out.println("======================");
                System.out.println("|     MENU PROGRAM   |");
                System.out.println("======================");
                System.out.println("1. Lihat Database ");
                System.out.println("2. Input Database ");
                System.out.println("3. Update Database ");
                System.out.println("4. Cari Database ");
                System.out.println("5. Hapus Database ");
                System.out.print(" Masukkan Pilihan Program : ");
                choice = inputuser.next();
                switch(choice){
                    case "1":
                    pjl.seeData();
                    break;
                    case "2":
                    pjl.addData();
                    break;
                    case "3":
                    pjl.update();
                    break;
                    case "4":
                    pjl.searchData();
                    break;
                    case "5":
                    pjl.clear();
                    break;
                    default :
                    System.err.println("MENU TIDAK TERSEDIA");
                }
                System.out.println("\n Close Program [y/n]? : ");
                choice = inputuser.next();
                keadaan = choice.equalsIgnoreCase("n");
            }
            System.out.println("KHAMSAMIDA");
        }
        catch(ClassNotFoundException ex){
            System.err.println("DRIVER Tidak Ditemukan");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("TIDAK TERHUBUNG");
        }
    }
}
