package org.example.ClienteDao;

import org.example.Services.ConnectionFactory;
import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {


    private ConnectionFactory connectionFactory = new ConnectionFactory();

    //Listando Clientes
    public List<Cliente> listaTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from cliente";

        Connection pegaconexao = connectionFactory.criaConexao();

        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            //percorrendo lista de clientes
            while (resultSet.next()){
                //Apartir do resultset vamos popular variaives
                Long id = resultSet.getLong("id");
                String cpfcnpj = resultSet.getString("cpfcnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
//                String logradouro = resultSet.getString("logradouro");
//                String bairro = resultSet.getString("bairro");
//
//                String estado = resultSet.getString("estado");
//                String cep = resultSet.getString("cep");

                //instaciar cliente
                Cliente cliente = new Cliente(id,  nome,  cidade,  email,  telefone,  cpfcnpj);

                lista.add(cliente);

            }
            //executa
            ps.execute();

            //fecha conexao
            ps.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }

    //Efetuando Cadastro de novos Clientes
    public void cadastra(Cliente novoCliente) {
        //Query de cadastro
        String sql = """
                     insert into cliente 
                        (nome, cidade, email, telefone, cpfcnpj) 
                     values
                        (?, ?, ?, ?, ?)
                     """;

        //Instacia classe das concecções


        try {
            //pega conexao
            Connection pegaConcexao = connectionFactory.criaConexao();

            //roda query no banco de dados
            PreparedStatement ps = pegaConcexao.prepareStatement(sql);

            //seta os campos
            ps.setString(1, novoCliente.getNome());
            ps.setString(2, novoCliente.getCidade());
            ps.setString(3, novoCliente.getEmail());
            ps.setString(4, novoCliente.getTelefone());
            ps.setString(5, novoCliente.getCpfcnpj());

            //executa
            ps.execute();

            //fecha conexao
            ps.close();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void exclusao(Cliente cliente    ){

    }

    public Cliente  pesquisaPorId(Long id){
        //query de busca
        String sql = "select * from cliente where id = ?";

        //busca da conexao
        //Connection pegaconexao = connectionFactory.criaConexao();

        try {

            //busca da conexao
            Connection pegaconexao = connectionFactory.criaConexao();
            //rodando a query
            PreparedStatement ps = pegaconexao.prepareStatement(sql);

            //setando paramentro
            ps.setLong(1,id);

            //resultado da query
            ResultSet resultSet = ps.executeQuery();

            Cliente cliente = null;

            if (resultSet.next()) {
                //cliente = montaCliente(resultSet);
                //Apartir do resultset vamos popular variaives
                Long idencontrado = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String cpfcnpj = resultSet.getString("cpfcnpj");
                String cidade = resultSet.getString("cidade");


                //instaciar cliente
                cliente = new Cliente(idencontrado,  nome,  cidade,  email,  telefone,  cpfcnpj);
            }

            ps.close();
            resultSet.close();

            return cliente;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    //metodo para exclusao
    public void excluir(Cliente clienteExcluir){
        String sql = "delete from cliente where id = ?";

        Connection pegaconexao = connectionFactory.criaConexao();
        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ps.setLong(1, clienteExcluir.getId());

            ps.execute();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException("Error ao excluir cliente",  e);

        }

    }

    public void alteracao(Long id, Cliente clienteParaAlterar) {

        String sql = """
                     update cliente set 
                        nome = ?, 
                        cidade = ?, 
                        email = ?, 
                        telefone = ?, 
                        cpfcnpj = ?
                     where id = ?
                     """;

        Connection pegaconexao = connectionFactory.criaConexao();


        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ps.setString(1, clienteParaAlterar.getNome());
            ps.setString(3, clienteParaAlterar.getEmail());
            ps.setString(4, clienteParaAlterar.getTelefone());
            ps.setString(2, clienteParaAlterar.getCidade());
            ps.setString(5, clienteParaAlterar.getCpfcnpj());

            ps.setLong(6, id);


            ps.execute();
            ps.close();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }
}
