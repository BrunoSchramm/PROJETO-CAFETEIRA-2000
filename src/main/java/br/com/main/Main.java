package br.com.main;

import br.com.dao.CafeDAO;
import br.com.dao.CafeteiraDAO;
import br.com.dao.CompradorDAO;
import br.com.dao.DonoDeMaquinaDAO;
import br.com.dao.PedidoDAO;
import br.com.model.Cafe;
import br.com.model.Cafeteira;
import br.com.model.Comprador;
import br.com.model.DonoDeMaquina;
import br.com.model.Pedido;
import br.com.model.StatusMaquina;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   TESTE DO SISTEMA CAFETEIRA AUTOMÁTICA  ");
        System.out.println("==========================================");

        // Instanciando os DAOs
        DonoDeMaquinaDAO donoDAO = new DonoDeMaquinaDAO();
        CafeteiraDAO cafeteiraDAO = new CafeteiraDAO();
        CafeDAO cafeDAO = new CafeDAO();
        CompradorDAO compradorDAO = new CompradorDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        // 1. Cadastrando um Dono de Máquina
        System.out.println("\n--- 1. Cadastrando Dono da Máquina ---");
        DonoDeMaquina dono = new DonoDeMaquina(0, "Mestre do Café", true);
        donoDAO.salvar(dono);

        // 2. Cadastrando uma Cafeteira (associada ao dono de ID 1)
        System.out.println("\n--- 2. Cadastrando Cafeteira ---");
        Cafeteira cafeteira = new Cafeteira(0, 1, 2000, 500, StatusMaquina.PRONTA);
        cafeteiraDAO.salvar(cafeteira);

        // 3. Cadastrando opções no Cardápio
        System.out.println("\n--- 3. Cadastrando Cafés no Cardápio ---");
        Cafe espresso = new Cafe(0, "Espresso Duplo", 6.50, 60, 15);
        Cafe cappuccino = new Cafe(0, "Cappuccino Italiano", 8.00, 100, 20);
        cafeDAO.salvar(espresso);
        cafeDAO.salvar(cappuccino);

        // 4. Cadastrando um Comprador
        System.out.println("\n--- 4. Cadastrando Comprador ---");
        Comprador comprador = new Comprador(0, "Dev Faminto", 50.00);
        compradorDAO.salvar(comprador);

        // 5. Simulando a realização de um Pedido
        System.out.println("\n--- 5. Registrando Pedido ---");
        // Pedido com cafeteira 1, café 1, comprador 1
        Pedido novoPedido = new Pedido(0, 1, 1, 1, null);
        pedidoDAO.salvar(novoPedido);

        // 6. Consultando e exibindo os dados salvos no banco
        System.out.println("\n==========================================");
        System.out.println("        CAFÉS DISPONÍVEIS NO BANCO        ");
        System.out.println("==========================================");
        
        List<Cafe> listaCafes = cafeDAO.listarTodos();
        for (Cafe c : listaCafes) {
            System.out.println("ID: " + c.getId() + 
                               " | Nome: " + c.getNome() + 
                               " | Preço: R$ " + String.format("%.2f", c.getPreco()) + 
                               " | Água: " + c.getAguaNecessaria() + "ml" + 
                               " | Grãos: " + c.getGraosNecessarios() + "g");
        }
    }
}