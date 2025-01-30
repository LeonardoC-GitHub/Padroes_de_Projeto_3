import java.util.Observable;
import java.util.Observer;

public class Cliente implements Observer {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Pedido) {
            Pedido pedido = (Pedido) o;
            System.out.println("Cliente " + nome + " foi notificado. Estado atual do pedido: " + pedido.getEstado());
        }
    }
}
