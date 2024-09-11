import domain.Carrinho;
import domain.Item;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App{

    public static List<Item> inicializarItensPredefinidos() {
        List<Item> itensDisponiveis = new ArrayList<>();
        itensDisponiveis.add(new Item("001", "Mouse", 50.0,0,0));
        itensDisponiveis.add(new Item("002", "Teclado", 100.0,0,0));
        itensDisponiveis.add(new Item("003", "Monitor", 750.0,0,0));
        itensDisponiveis.add(new Item("004", "Cadeira Gamer", 500.0,0,0));
        itensDisponiveis.add(new Item("005", "Fone de Ouvido", 150.0,0,0));
        itensDisponiveis.add(new Item("006", "Notebook", 3000.0,0,0));
        itensDisponiveis.add(new Item("007", "Impressora", 200.0,0,0));
        return itensDisponiveis;
    }

    public static String exibirMenu() {
        return JOptionPane.showInputDialog(null,
                "Escolha uma opção:\n"
                        + "1. Inserir item ao carrinho\n"
                        + "2. Acréscimo de item\n"
                        + "3. Desconto de item\n"
                        + "4. Acréscimo total\n"
                        + "5. Desconto total\n"
                        + "6. Listar itens do carrinho\n"
                        + "7. Listar itens disponíveis\n"
                        + "8. Finalizar venda\n",
                "Menu", JOptionPane.PLAIN_MESSAGE);
    }

    public static void inserirItem(Carrinho carrinho, List<Item> itensDisponiveis) {
        StringBuilder mensagem = new StringBuilder("Itens disponíveis:\n");
        for (int i = 0; i < itensDisponiveis.size(); i++) {
            Item item = itensDisponiveis.get(i);
            mensagem.append(i + 1).append(". ")
                    .append(item.getDescricao()).append(" (Código: ")
                    .append(item.getCodigo()).append(", Valor: ")
                    .append(String.format("%.2f", item.getValor())).append(")\n");
        }

        String escolha = JOptionPane.showInputDialog(null, mensagem.toString(), "Escolha o item para adicionar ao carrinho",
                JOptionPane.PLAIN_MESSAGE);

        try {
            int indice = Integer.parseInt(escolha) - 1;
            if (indice >= 0 && indice < itensDisponiveis.size()) {
                Item itemEscolhido = itensDisponiveis.get(indice);
                carrinho.adicionarItem(itemEscolhido);
                JOptionPane.showMessageDialog(null, "Item '" + itemEscolhido.getDescricao() + "' adicionado ao carrinho.");
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida!");
        }
    }

    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho();
        List<Item> itensDisponiveis = inicializarItensPredefinidos();

        while (true) {
            String opcao = exibirMenu();
            if (opcao == null) {
                break;
            }

            switch (opcao) {
                case "1":
                    inserirItem(carrinho, itensDisponiveis);
                    break;
                case "2":
                    String codigoAcrescimo = JOptionPane.showInputDialog("Código do item:");
                    double valorAcrescimo = Double.parseDouble(JOptionPane.showInputDialog("Valor do acréscimo:"));
                    carrinho.aplicarAcrescimoItem(codigoAcrescimo, valorAcrescimo);
                    break;
                case "3":
                    String codigoDesconto = JOptionPane.showInputDialog("Código do item:");
                    double valorDesconto = Double.parseDouble(JOptionPane.showInputDialog("Valor do desconto:"));
                    carrinho.aplicarDescontoItem(codigoDesconto, valorDesconto);
                    break;
                case "4":
                    double acrescimoTotal = Double.parseDouble(JOptionPane.showInputDialog("Valor do acréscimo total:"));
                    carrinho.distribuirAcrescimoTotal(acrescimoTotal);
                    break;
                case "5":
                    double descontoTotal = Double.parseDouble(JOptionPane.showInputDialog("Valor do desconto total:"));
                    carrinho.distribuirDescontoTotal(descontoTotal);
                    break;
                case "6":
                    carrinho.listarItens();
                    break;
                case "7":
                    StringBuilder mensagem = new StringBuilder("Itens disponíveis:\n");
                    for (Item item : itensDisponiveis) {
                        mensagem.append(item.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, mensagem.toString());
                    break;
                case "8":
                    carrinho.finalizarVenda();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        }
    }
}