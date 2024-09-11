package domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Item {
    private String codigo;
    private String descricao;
    private double valor;
    private double acrescimo = 0;
    private double desconto = 0;

    public void aplicarAcrescimo(double acrescimo) {
        this.acrescimo += acrescimo;
        this.valor += acrescimo;
    }

    public void aplicarDesconto(double desconto) {
        this.desconto += desconto;
        this.valor -= desconto;
    }
}