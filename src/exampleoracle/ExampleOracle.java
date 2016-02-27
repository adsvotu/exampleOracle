package exampleoracle;

import static dao.JDBCConnection.getConnectOracle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Basseti
 */
public class ExampleOracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            Connection conn = getConnectOracle();
            
            String sql = "SELECT * FROM Cliente WHERE nome_cliente LIKE ?";
            PreparedStatement prdStmtPesq = conn.prepareStatement(sql);
            prdStmtPesq.setString(1, "R%");
            ResultSet rsPesq = prdStmtPesq.executeQuery();
            
            while (rsPesq.next()) {
                System.out.print("Codigo: "+rsPesq.getString("Codigo_Cliente"));
                System.out.println(" Nome: "+rsPesq.getString("Nome_Cliente"));
            }

            
            
            String sqlUpdate = "INSERT INTO Cliente( "+
                    "Codigo_Cliente, Nome_Cliente, Endereco, Cidade, CEP, UF, CNPJ, IE) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prdStmtPost = conn.prepareStatement(sqlUpdate);
            prdStmtPost.setInt(1, 17);
            prdStmtPost.setString(2, "Maria");
            prdStmtPost.setString(3, "Centro");
            prdStmtPost.setString(4, "Votuporanga");
            prdStmtPost.setString(5, "15500000");
            prdStmtPost.setString(6, "SP");
            prdStmtPost.setString(7, "32816985/7465-6");
            prdStmtPost.setString(8, "2222");
            prdStmtPost.executeUpdate();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ExampleOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExampleOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExampleOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
