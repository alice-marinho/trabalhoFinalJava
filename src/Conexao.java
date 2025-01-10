import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL ="jdbc:mysql://localhost:3306/academiajava";
    private static final String USER ="root";
    private static final String PASSWORD ="root";

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(ClassNotFoundException e){
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            return null;
        } catch (SQLException e){
            System.out.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
            return null;
        }

    }


}
