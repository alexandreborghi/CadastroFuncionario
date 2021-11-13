
package Controller;

import Model.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
public class EmpresaDAO {
    
    //establecer a conexao com o banco
    private final Connection co;
    
    //enviar o comando sql para o banco
    private PreparedStatement cmd;
    
    public EmpresaDAO(){
        this.co = Conexao.conectar();
    }
    //
    //inserir
    //
     public int inserirEmpresa(Empresa obj1){
        try {
            String SQL = "insert into tb_empresa "
                       + "(nome,pais) values (?,?)";//
            
            cmd = co.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            cmd.setString(1, obj1.getNome());//se estiver correto continua execucao
            cmd.setString(2, obj1.getPais());
            
            if (cmd.executeUpdate() > 0){//envia a instrução sql para o banco
                //operação realizada com sucesso
                return 1;   //OK
            }else{
                return -1;  // ERRO
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(co);
        }
    }
     
     //
    //ATUALIZAR
    //
     public int atualizarEmpresa(Empresa obj){
        try {
            String SQL = "update tb_empresa set nome=?, pais=?  where id=?";
            
            cmd = co.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            cmd.setString(1, obj.getNome());//se estiver correto continua execucao
            cmd.setString(2,obj.getPais());
            cmd.setInt(3,obj.getId());
            
            if (cmd.executeUpdate() > 0){//envia a instrução sql para o banco
                //operação realizada com sucesso
                return 1;   //OK
            }else{
                return -1;  // ERRO
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(co);
        }
    }
     
     //
    // LISTAR
    // Retorna todos os dados da tabela
    public List<Empresa> listarEmpresa(){
        try {
            String SQL = "select * from tb_empresa order by id";
            cmd = co.prepareStatement(SQL);
            
            //executar a consulta
            List<Empresa> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                Empresa emp = new Empresa();
                emp.setId(rs.getInt("id"));
                emp.setNome(rs.getString("nome"));
                emp.setPais(rs.getString("pais"));
                lista.add(emp);
            }
            return lista;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(co);
        }
    }
    
    
    //
    // pesquisar por nome
    //pesquisa empresa por nome
    public List<Empresa> pesquisarPorNomeEmpresa(String nome){
        try {
            String SQL = "select * from tb_empresa where nome ilike ? order by id";
            cmd = co.prepareStatement(SQL);
            cmd.setString(1,"%"+nome+"%");
            //executar a consulta
            List<Empresa> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                Empresa emp = new Empresa();
                emp.setId(rs.getInt("id"));
                emp.setNome(rs.getString("nome"));
                emp.setPais(rs.getString("pais"));
                lista.add(emp);
            }
            return lista;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(co);
        }
    }
     
     
    //
    // pesquisar por id
    //pesquisa empresa por id
    public Empresa pesquisarPorIdEmpresa(String id){
        try {
            String SQL = "select * from tb_empresa where id = ? order by id";
            cmd = co.prepareStatement(SQL);
            cmd.setInt(1, Integer.parseInt(id));
            //executar a consulta
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            if (rs.next()){//passa por apenas 1 id
                Empresa emp = new Empresa();
                emp.setId(rs.getInt("id"));
                emp.setNome(rs.getString("nome"));
                emp.setPais(rs.getString("Pais"));
                return emp;
            }
            return null;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(co);
        }
    }
}
