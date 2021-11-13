
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    //PARAMETROS
    private static final String HOST = "localhost:5432";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123456";
    private static final String DATABASE = "CONTROLE_FUNCIONARIO";
    
    //string d conexao
    private static final String URL =
       "jdbc:postgresql://" + HOST + "/" + DATABASE;
    //    
    //CONECTAR
    //ESTABELECE UMA NOVA CONEXAO COM SGBD
    public static Connection conectar(){
        try{
            //retrnar uma conexao
            return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
            );
            
        }catch(Exception erro){
            //ERRO
            System.err.println("ERRO: "+ erro.getMessage());
            return null;
            
        }
    }
    //
    //desconectar
    //finaliza conexao com o banco
    public static void desconectar (Connection con){
        try{
            //se a conexão está ativa
            if ( con != null){
                con.close();
            }
        }catch(Exception erro){
            System.err.println("ERRO: " + erro.getMessage());
        }
    }
}
