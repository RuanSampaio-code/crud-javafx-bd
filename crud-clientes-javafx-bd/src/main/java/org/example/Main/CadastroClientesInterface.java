package org.example.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Crud.ImplementaCRUD;
import org.example.model.Cliente;

public class CadastroClientesInterface extends Application {

    private ImplementaCRUD gerenciaCrud;

    @Override
    public void start(Stage primaryStage) {

        gerenciaCrud = new ImplementaCRUD(primaryStage);

        // Layout principal
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); // Centraliza os botões verticalmente

        // Botões -  Menu
        Button btnCadastrarPF = new Button("Cadastrar Cliente Pessoa Física");
        Button btnCadastrarPJ = new Button("Cadastrar Cliente Pessoa Jurídica");
        Button btnBuscarCliente = new Button("Buscar Cliente");
        Button btnAlterarCliente = new Button("Alterar Cliente");
        Button btnRemoverCliente = new Button("Remover Cliente");

        // Adicionando os botões ao layout
        root.getChildren().addAll(btnCadastrarPF, btnCadastrarPJ, btnBuscarCliente, btnAlterarCliente, btnRemoverCliente);

        // Configurando os eventos dos botões (ainda precisam ser implementados)
        btnCadastrarPF.setOnAction(e -> cadastrarClientePF());
        btnCadastrarPJ.setOnAction(e -> cadastrarClientePJ());
        btnBuscarCliente.setOnAction(e -> buscarClientes());
        btnAlterarCliente.setOnAction(e -> alterarCliente());
        btnRemoverCliente.setOnAction(e -> removerCliente());

        // Configurando a cena
        Scene scene = new Scene(root, 500, 400);

        // Configurando o palco
        primaryStage.setTitle("Cadastro de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarClientePF() {
        Stage stage = new Stage();

        // Janela modal impede interação com a janela principal enquanto estiver aberta
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Cadastro de Cliente Pessoa Física");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblCpf = new Label("CPF:");
        TextField txtcpf = new TextField();

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblCidade = new Label("Cidade:");
        TextField txtCidade = new TextField();

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();

        Button btnCadastrar = new Button("Cadastrar");

        btnCadastrar.setOnAction(e -> {
            // Aqui você pode implementar a lógica para salvar os dados no banco de dados ou em algum outro lugar

            String nomeCliPf    = txtNome.getText();
            String cidadeClipf  = txtCidade.getText();
            String emailClipf   = txtEmail.getText();
            String foneClipf    = txtTelefone.getText();
            String cpfCliepf    = txtcpf.getText();

            stage.close();

            gerenciaCrud.criarCliente(new Cliente(null, nomeCliPf, cidadeClipf, emailClipf, foneClipf, cpfCliepf));

            // Fecha a janela após o cadastro
        });

        layout.getChildren().addAll(lblCpf, txtcpf,lblNome, txtNome, lblCidade, txtCidade, lblEmail, txtEmail, lblTelefone, txtTelefone,  btnCadastrar);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.showAndWait(); // Mostra a janela e espera até que ela seja fechada
    }



    private void cadastrarClientePJ() {
        System.out.println("Botão 'Cadastrar Cliente Pessoa Jurídica' clicado.");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Janela modal impede interação com a janela principal enquanto estiver aberta
        stage.setTitle("Cadastro de Cliente Pessoa Física");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblCnpj = new Label("CNPJ:");
        TextField txtCnpj = new TextField();

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblCidade = new Label("Cidade:");
        TextField txtCidade = new TextField();

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();

        Button btnCadastrar = new Button("Cadastrar");

        btnCadastrar.setOnAction(e -> {

            System.out.println("Dados do cliente cadastrados:");
            System.out.println("Nome: " + txtNome.getText());
            System.out.println("Cidade: " + txtCidade.getText());
            System.out.println("Email: " + txtEmail.getText());
            System.out.println("Telefone: " + txtTelefone.getText());
            System.out.println("CNPJ: " + txtCnpj.getText());

            String nomeCliPf    = txtNome.getText();
            String cidadeClipf  = txtCidade.getText();
            String emailClipf   = txtEmail.getText();
            String foneClipf    = txtTelefone.getText();
            String cnpjCli      = txtCnpj.getText();

            stage.close();

            gerenciaCrud.criarCliente(new Cliente(null,nomeCliPf, cidadeClipf, emailClipf, foneClipf, cnpjCli));

        });

        layout.getChildren().addAll(lblCnpj, txtCnpj, lblNome, txtNome, lblCidade, txtCidade, lblEmail, txtEmail, lblTelefone, txtTelefone,  btnCadastrar);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.showAndWait(); // Mostra a janela e espera até que ela seja fechada
    }

    private void buscarClientes() {
        System.out.println("Botão 'Buscar Cliente' clicado.");
        gerenciaCrud.buscarClientes();
    }

    private void alterarCliente() {
        System.out.println("Botão 'Alterar Cliente' clicado.");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Janela modal impede interação com a janela principal enquanto estiver aberta
        stage.setTitle("Alterando cliente");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblcpf = new Label("Digite ID para alterar:");
        TextField txtid = new TextField();

        Button btnAlterar = new Button("Alterar");

        btnAlterar.setOnAction(e -> {
            // Aqui você pode implementar a lógica para salvar os dados no banco de dados ou em algum outro lugar

            Long idcliente = Long.valueOf(txtid.getText());

            Cliente novoCliente = new Cliente(null," ", " ", " ", " ", "");
            stage.close();
            gerenciaCrud.atualizarCliente(idcliente, novoCliente);

            // Fecha a janela após o cadastro
        });


        layout.getChildren().addAll( lblcpf, txtid, btnAlterar );
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.showAndWait(); // Mostra a janela e espera até que ela seja fechada
        stage.close();





    }

    private void removerCliente() {
        System.out.println("Botão 'Remover Cliente' clicado.");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Janela modal impede interação com a janela principal enquanto estiver aberta
        stage.setTitle("Excluindo cliente");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblid = new Label("Digite o Id para deletar:");
        TextField txtId = new TextField();

        Button btnDeletar = new Button("Apagar");

        btnDeletar.setOnAction(e -> {
            // Aqui você pode implementar a lógica para salvar os dados no banco de dados ou em algum outro lugar

            Long id = Long.valueOf(txtId.getText());
            stage.close();

            gerenciaCrud.deletarCliente(id);

            // Fecha a janela após o cadastro
        });


        layout.getChildren().addAll( lblid, txtId, btnDeletar );
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.showAndWait(); // Mostra a janela e espera até que ela seja fechada
        stage.close();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
