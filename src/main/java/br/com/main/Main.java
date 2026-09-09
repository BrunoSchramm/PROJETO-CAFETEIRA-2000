package br.com.main;

import java.util.List;
import java.util.Scanner;

import br.com.dao.CafeDAO;
import br.com.dao.CafeteiraDAO;
import br.com.dao.CompradorDAO;
import br.com.dao.DonoDeMaquinaDAO;
import br.com.dao.PedidoDAO;
import br.com.factory.DatabaseUtils;
import br.com.model.Cafe;
import br.com.model.Cafeteira;
import br.com.model.Comprador;
import br.com.model.DonoDeMaquina;
import br.com.model.Pedido;
import br.com.model.StatusMaquina;

public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Pergunta interativa no Console
        System.out.print("Deseja limpar as tabelas antes de executar os testes? (S/N): ");
        String opcao = scanner.nextLine().trim();

        System.out.println(); // Linha em branco para organizar o visual

        if (opcao.equalsIgnoreCase("S")) {
            DatabaseUtils.limparTabelas();
        } else {
            System.out.println("--- Mantendo os dados existentes no banco de dados. ---\n");
        }

        System.out.println("==========================================");
        System.out.println("   TESTE DO SISTEMA CAFETEIRA AUTOMÁTICA  ");
        System.out.println("==========================================");

        // Instanciando os DAOs
        DonoDeMaquinaDAO donoDAO = new DonoDeMaquinaDAO();
        CafeteiraDAO cafeteiraDAO = new CafeteiraDAO();
        CafeDAO cafeDAO = new CafeDAO();
        CompradorDAO compradorDAO = new CompradorDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        // 1. Cadastrando Dono da Máquina
        System.out.println("\n--- 1. Cadastrando Dono da Máquina ---");
        DonoDeMaquina dono = new DonoDeMaquina(0, "Mestre do Café", true);
        donoDAO.salvar(dono);

        // 2. Cadastrando Cafeteira associada ao Dono ID 1
        System.out.println("\n--- 2. Cadastrando Cafeteira ---");
        Cafeteira cafeteira = new Cafeteira(0, 1, 2000, 1000, StatusMaquina.PRONTA);
        cafeteiraDAO.salvar(cafeteira);

        // 3. Cadastrando Cafés no Cardápio
        System.out.println("\n--- 3. Cadastrando Cafés no Cardápio ---");
        Cafe espresso = new Cafe(0, "Espresso Tradicional", 4.50, 50, 15);
        Cafe cappuccino = new Cafe(0, "Cappuccino Especial", 7.00, 100, 20);
        cafeDAO.salvar(espresso);
        cafeDAO.salvar(cappuccino);

        // 4. Cadastrando um Comprador
        System.out.println("\n--- 4. Cadastrando Comprador ---");
        Comprador comprador = new Comprador(0, "Lucas Santos", 30.00);
        compradorDAO.salvar(comprador);

        // 5. Registrando um Pedido (Cafeteira 1, Café 1, Comprador 1)
        System.out.println("\n--- 5. Registrando Pedido ---");
        Pedido novoPedido = new Pedido(0, 1, 1, 1, null);
        pedidoDAO.salvar(novoPedido);

        // 6. Consultando e exibindo os cafés gravados no banco
        System.out.println("\n==========================================");
        System.out.println("        CAFÉS DISPONÍVEIS NO BANCO        ");
        System.out.println("==========================================");
        List<Cafe> listaCafes = cafeDAO.listarTodos();
        for (Cafe c : listaCafes) {
            System.out.println("ID: " + c.getId() + 
                               " | Nome: " + c.getNome() + 
                               " | Preço: R$ " + c.getPreco() + 
                               " | Água: " + c.getAguaNecessaria() + "ml" + 
                               " | Grãos: " + c.getGraosNecessarios() + "g");
        }

        scanner.close();
    }
}