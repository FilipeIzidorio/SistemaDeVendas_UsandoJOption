package domain;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Item> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O carrinho está vazio.");
        } else {
            StringBuilder mensagem = new StringBuilder("Itens no carrinho:\n");
            for (Item item : itens) {
                mensagem.append(item.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }

    public Item buscarItemPorCodigo(String codigo) {
        for (Item item : itens) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    public void aplicarAcrescimoItem(String codigo, double acrescimo) {
        Item item = buscarItemPorCodigo(codigo);
        if (item != null) {
            item.aplicarAcrescimo(acrescimo);
            JOptionPane.showMessageDialog(null, "Acréscimo aplicado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Item não encontrado.");
        }
    }

    public void aplicarDescontoItem(String codigo, double desconto) {
        Item item = buscarItemPorCodigo(codigo);
        if (item != null) {
            item.aplicarDesconto(desconto);
            JOptionPane.showMessageDialog(null, "Desconto aplicado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Item não encontrado.");
        }
    }

    public void distribuirAcrescimoTotal(double acrescimoTotal) {
        if (itens.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Carrinho vazio.");
            return;
        }
        double acrescimoPorItem = acrescimoTotal / itens.size();
        for (Item item : itens) {
            item.aplicarAcrescimo(acrescimoPorItem);
        }
        JOptionPane.showMessageDialog(null, "Acréscimo total aplicado.");
    }

    public void distribuirDescontoTotal(double descontoTotal) {
        if (itens.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Carrinho vazio.");
            return;
        }
        double descontoPorItem = descontoTotal / itens.size();
        for (Item item : itens) {
            item.aplicarDesconto(descontoPorItem);
        }
        JOptionPane.showMessageDialog(null, "Desconto total aplicado.");
    }

    public void finalizarVenda() {
        double totalAcrescimo = 0;
        double totalDesconto = 0;
        double valorFinal = 0;

        StringBuilder mensagem = new StringBuilder("Resumo da venda:\n");
        for (Item item : itens) {
            mensagem.append(item).append("\n");
            totalAcrescimo += item.getAcrescimo();
            totalDesconto += item.getDesconto();
            valorFinal += item.getValor();
        }

        mensagem.append("\nAcréscimo total: ").append(String.format("%.2f", totalAcrescimo))
                .append("\nDesconto total: ").append(String.format("%.2f", totalDesconto))
                .append("\nValor final: ").append(String.format("%.2f", valorFinal));

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
}