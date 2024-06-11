package org.example.Interface;

import org.example.model.Cliente;

public interface ClienteCRUD {
    // Método para criar um novo cliente
    void criarCliente(Cliente cliente);

    // Método para todos os clientes
    void buscarClientes();

    // Método para atualizar os dados de um cliente
    void atualizarCliente(Long id, Cliente novoCliente);

    // Método para deletar um cliente
    void deletarCliente(Long id);

}