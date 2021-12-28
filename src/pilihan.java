import java.sql.SQLException;
interface pilihan {
    void addData() throws SQLException;
    void seeData() throws SQLException;
    void searchData() throws SQLException;
    void clear();
    void update() throws SQLException;    
}
