package org.example.Crud;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import org.example.Interface.ClienteCRUD;
import org.example.Services.ClienteService;
import org.example.model.Cliente;


public class ImplementaCRUD implements ClienteCRUD {

    private final static ClienteService clienteService = new ClienteService();

    private List<Cliente> contas = new ArrayList<>();
    private TableView<Cliente> tabelaClientes;
    private Stage primaryStage; // Variável para armazenar o palco principal

    // Construtor que recebe o palco principal como parâmetro
    public ImplementaCRUD(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void criarCliente(Cliente cliente) {
        //contas.add(cliente);
        clienteService.efetuaCadastroDeCliente( cliente);

        exibirAlerta("feito", "cadastro feito com suceso");

    }



    @Override
    public void atualizarCliente(Long id, Cliente novoCliente) {
        List<Cliente> contasbd = clienteService.listarCadastroDeClientes();
        // Percorre a lista de clientes
        for (Cliente cliente : contasbd) {
            // Se encontrar um cliente com o CPF fornecido, atualiza os dados do cliente
            if (cliente.getId().equals(id)) {
                // Configura o novo cliente com os dados fornecidos
                cliente.setNome(novoCliente.getNome());
                cliente.setCidade(novoCliente.getCidade());
                cliente.setEmail(novoCliente.getEmail());
                cliente.setTelefone(novoCliente.getTelefone());
                cliente.setCpfcnpj(novoCliente.getCpfcnpj());

                // Exibe um formulário de atualização do cliente com os novos dados
                exibirFormularioAtualizacao(cliente);

                // Exibe uma mensagem indicando que o cliente foi alterado com sucesso
                System.out.println("Cliente com id " + id + " alterado com sucesso.");

                return; // Interrompe o loop após alterar o cliente
            }
        }
        // Se não encontrar nenhum cliente com o CPF fornecido, exibe uma mensagem de erro
        exibirAlerta("Alteração de Cliente", "Cliente com Id " + id + " não encontrado na lista.");
    }


    @Override
    public void deletarCliente(Long id) {

        List<Cliente> contasbd = clienteService.listarCadastroDeClientes();
        // Percorre a lista de clientes
        for (int i = 0; i < contasbd.size(); i++) {
            Cliente cliente = contasbd.get(i);

            // Se encontrar um cliente com o CPF fornecido, remove da lista
            if (cliente.getId().equals(id)) {
                clienteService.excluiClientePeloId(id);
                /* System.out.println("Cliente com CPF " + cpfcnpj + " removido com sucesso."); */
                exibirAlerta("Sucesso", "ID:" + id + "deletado com sucesso");
                return; // Interrompe o loop após remover o cliente
            }
        }
        // Se não encontrar nenhum cliente com o CPF fornecido, exibe uma mensagem de erro
        System.out.println();
        exibirAlerta("Erro", "Cliente com CPF " + id + " não encontrado na lista.");

    }

    @Override
    public void buscarClientes() {
        List<Cliente> contasbd = clienteService.listarCadastroDeClientes();

        // Criar um novo palco para exibir a tabela de clientes
        Stage stageTabelaClientes = new Stage();
        stageTabelaClientes.setTitle("Lista de Clientes");

        // Cria uma TableView para exibir os clientes
        tabelaClientes = new TableView<>();

        // Cria as colunas da tabela
        TableColumn<Cliente, String> colunaId = new TableColumn<>("ID");
        TableColumn<Cliente, String> colunaCPFCNPJ = new TableColumn<>("CPF / CNPJ");
        TableColumn<Cliente, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Cliente, String> colunaCidade = new TableColumn<>("Cidade");
        TableColumn<Cliente, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Cliente, String> colunaTelefone = new TableColumn<>("Telefone");

        // Configura as células das colunas para obter os valores corretos do objeto Cliente
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaCPFCNPJ.setCellValueFactory(new PropertyValueFactory<>("cpfcnpj"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Adiciona as colunas à tabela
        tabelaClientes.getColumns().addAll(colunaId,colunaCPFCNPJ, colunaNome, colunaCidade, colunaEmail, colunaTelefone);

        // Adiciona os clientes à tabela
        tabelaClientes.getItems().addAll(contasbd);

        // Criar o layout da cena
        StackPane root = new StackPane();
        root.getChildren().add(tabelaClientes);

        // Criar a cena
        Scene scene = new Scene(root, 600, 200);

        // Configurar a cena no novo palco e exibi-lo
        stageTabelaClientes.setScene(scene);
        stageTabelaClientes.show();
    }


    // Método para exibir um alerta simples
    private void exibirAlerta(String titulo, String mensagem) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setTitle(titulo);

        Label lblMensagem = new Label(mensagem);
        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> alertStage.close());

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(lblMensagem, btnOK);
        alertLayout.setAlignment(Pos.CENTER);
        alertLayout.setPadding(new Insets(20));

        Scene alertScene = new Scene(alertLayout, 300, 100);
        alertStage.setScene(alertScene);
        alertStage.show();
    }


    // Método para exibir um formulário de atualização do cliente
    private void exibirFormularioAtualizacao(Cliente cliente) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Atualizar Cliente");

        // Elementos de layout para o formulário de atualização
        Label lblCpfCpnpj = new Label("CPF/CNPJ:");
        TextField txtcpfcnpj = new TextField(cliente.getCidade());

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField(cliente.getNome());

        Label lblCidade = new Label("Cidade:");
        TextField txtCidade = new TextField(cliente.getCidade());

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField(cliente.getEmail());

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField(cliente.getTelefone());

        Button btnAtualizar = new Button("Atualizar");
        btnAtualizar.setOnAction(e -> {
            // Atualiza os dados do cliente com os dados inseridos no formulário
            cliente.setCpfcnpj(txtcpfcnpj.getText());
            cliente.setNome(txtNome.getText());
            cliente.setCidade(txtCidade.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());

            clienteService.alteracaoDeCliente(cliente.getId(), cliente);
            exibirAlerta("feito", "cadastro Alterado com suceso");

            // Fecha a janela após a atualização
            stage.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblCpfCpnpj, txtcpfcnpj,lblNome, txtNome, lblCidade, txtCidade, lblEmail, txtEmail, lblTelefone, txtTelefone, btnAtualizar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }


}
