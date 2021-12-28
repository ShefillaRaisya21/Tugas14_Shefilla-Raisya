import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.protocol.Resultset;
public class penjualan implements pilihan {
    static Connection conn;
    Long subbarang,diskonbarang,bayarbarang;
    String URL = "jdbc:mysql://localhost:3306/tugas";
    String user = "root";
    String password = "";
    Scanner scanner = new Scanner(System.in);
    @Override
    public void addData() throws SQLException {
        String program1 = "\n Tambah Data\t";
        System.out.println(program1.toUpperCase());
        boolean keadaan = true;
        do{
            try{
                System.out.println("Nama Barang : ");
                String nama = scanner.nextLine();
                System.out.println("Faktur Barang : ");
                String faktur = scanner.nextLine();
                System.out.println("Price Barang : ");
                Long price = scanner.nextLong();
                System.out.println("Jumlah Barang : ");
                Integer jumlah = scanner.nextInt();
                System.out.println("Number Barang : ");
                Integer number = scanner.nextInt();
                subbarang = price*jumlah;
                if(subbarang > 65000){
                    diskonbarang = subbarang*20/100;
                    System.out.println("Diskon yang didapatkan  : "+diskonbarang);
                }
                else if(subbarang > 50000 && subbarang <= 65000 ){
                    diskonbarang = subbarang*15/100;
                    System.out.println("Diskon yang didapatkan  : "+diskonbarang);
                }
                else if(subbarang > 35000 && subbarang <= 50000 ){
                    diskonbarang = subbarang*10/100;
                    System.out.println("Diskon yang didapatkan  : "+diskonbarang);
                }
                else if(subbarang > 25000 && subbarang <= 35000){
                    diskonbarang = subbarang*5/100;
                    System.out.println("Diskon yang didapatkan  : "+diskonbarang);
                }
                else{
                    diskonbarang = (long)(0);
                    System.out.println("Maaf Anda tidak Mendapatkan Diskon");
                }
                bayarbarang = subbarang - diskonbarang;
                String sql = "INSERT INTO pertemuan (number, NamaBarang, NoFaktur, HargaBarang, JumlahBarang, Subtotal, DiskonBarang, Bayar) VALUES ('"+number+"','"+nama+"','"+faktur+"','"+price+"','"+jumlah+"','"+subbarang+"','"+diskonbarang+"','"+bayarbarang+"')";
                conn = DriverManager.getConnection(URL,user,password);
                Statement state = conn.createStatement();
                state.execute(sql);
                keadaan = false;
            }
            catch(InputMismatchException ex){
                System.err.println("Inputan salah");
            }
            catch(SQLException e){
                System.err.println("Error");
            }
        }while(keadaan);
    }

    @Override
    public void clear() {
        String program5 = "\n Hapus Data\t";
        System.out.println(program5.toUpperCase());
        try{
            seeData();
            System.out.println("Inputkan Number : ");
            Integer number = Integer.parseInt(scanner.nextLine());
            String sql = "DELETE FROM pertemuan WHERE number = "+number;
            conn = DriverManager.getConnection(URL, user, password);
            Statement state = conn.createStatement();
            if(state.executeUpdate(sql) > 0){
                System.out.println("FINISHED CLEAR DATA");
            }
        }
        catch(SQLException e){
            System.err.println("TERJADI KESALAHAN");
        }
    }
    @Override
    public void searchData() throws SQLException {
       String program4 = "\n Search Data";
       System.out.println(program4.toUpperCase());
       try{
           System.out.println("Faktur : ");
           String keyword = scanner.nextLine();
           String sql = "SELECT * FROM pertemuan WHERE NoFaktur LIKE '%"+keyword+"'";
           conn = DriverManager.getConnection(URL, user, password);
           Statement state = conn.createStatement();
           ResultSet hasil = state.executeQuery(sql);
           while(hasil.next()){
                System.out.print("Number ke : ");
                System.out.println(hasil.getInt("number"));
                System.out.print("Barang : ");
                System.out.println(hasil.getString("NamaBarang"));
                System.out.print("faktur: ");
                System.out.println(hasil.getString("NoFaktur"));
                System.out.print("price: ");
                System.out.println(hasil.getInt("HargaBarang"));
                System.out.print("Jumlah : ");
                System.out.println(hasil.getInt("JumlahBarang"));
                System.out.print("Subtotal : ");
                System.out.println(hasil.getInt("Subtotal"));
                System.out.print("Harga Diskon : ");
                System.out.println(hasil.getInt("DiskonBarang"));
                System.out.print("Bayaran: ");
                System.out.println(hasil.getInt("Bayar"));
           }
       }
       catch(SQLException ex){
           System.out.println("Tidak Teridentifikasi");
       }
    }
    @Override
    public void seeData() throws SQLException {
        String program1 = "\n See Data";
        System.out.println(program1.toUpperCase());
        try{
            String sql = "SELECT * FROM pertemuan";
            conn = DriverManager.getConnection(URL, user, password);
            Statement state = conn.createStatement();
            ResultSet hasil = state.executeQuery(sql);
            while(hasil.next()){
                System.out.print("Number ke : ");
                System.out.println(hasil.getInt("number"));
                System.out.print("Barang : ");
                System.out.println(hasil.getString("NamaBarang"));
                System.out.print("faktur: ");
                System.out.println(hasil.getString("NoFaktur"));
                System.out.print("price: ");
                System.out.println(hasil.getInt("HargaBarang"));
                System.out.print("Jumlah : ");
                System.out.println(hasil.getInt("JumlahBarang"));
                System.out.print("Subtotal : ");
                System.out.println(hasil.getInt("Subtotal"));
                System.out.print("Harga Diskon : ");
                System.out.println(hasil.getInt("DiskonBarang"));
                System.out.print("Bayaran: ");
                System.out.println(hasil.getInt("Bayar"));
            }
        }
        catch(SQLException ex){
            System.out.println("Tidak Teridentifikasi");
        }
    }
    @Override
    public void update() throws SQLException {
        String program3 = "\n Update Data\t";
        System.out.println(program3.toUpperCase());
        try{
            seeData();
            System.out.println("Masukkan Number Data : ");
            Integer number = Integer.parseInt(scanner.nextLine());
            String sql = "SELECT * FROM pertemuan WHERE number = "+number;
            Statement state = conn.createStatement();
            ResultSet hasil = state.executeQuery(sql);
            if(hasil.next()){
                System.out.println("Nama Barang ["+hasil.getString("NamaBarang")+"]\t : ");
                String nama = scanner.nextLine();
                sql = "UPDATE pertemuan SET NamaBarang = '"+nama+"' WHERE number = '"+number+"'";
                if(state.executeUpdate(sql) > 0){
                    System.out.println("Complated change data");
                }
            }
            state.close();
        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan");
            System.err.println(e.getMessage());
        }
    }
}
