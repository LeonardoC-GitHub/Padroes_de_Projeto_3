import java.util.Observable;

public class Pedido extends Observable {
    private PedidoEstado estado;

    // Constructor: Initially, the state is "Em Preparo"
    public Pedido() {
        this.estado = PedidoEmPreparo.obterInstancia();  // Initial state is "Em Preparo"
    }

    // Getter for the current state of the Pedido
    public PedidoEstado getEstado() {
        return estado;
    }

    // Setter for changing the state and notifying observers
    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        setChanged();  // Marks the object as changed
        notifyObservers();  // Notify all registered observers
    }

    public void manipular() {
        System.out.println("Pedido está no estado: " + estado.getEstado());
    }

    public boolean preparar() {
        return estado.preparar(this);
    }

    public boolean aguardarTransporte() {
        return estado.aguardar(this);
    }

    public boolean transportar() {
        return estado.transportar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public boolean finalizarPedido() {
        return estado.finalizar(this);
    }
}
