
package Controller;

import java.sql.Connection;

public class conexaoTest {
    public static void main(String[] args) {
        //Conectar no SGBD
        Connection con = Conexao.conectar();
        if ( con != null){
            System.out.println("Conexão realizada com sucesso!");
            Conexao.desconectar(con);
        }
    }
}
