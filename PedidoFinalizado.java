public class PedidoFinalizado extends PedidoEstado {

    private static final PedidoFinalizado instancia = new PedidoFinalizado();

    private PedidoFinalizado() {}

    public static PedidoFinalizado obterInstancia() {
        return instancia;
    }

    @Override
    public String getEstado() {
        return "Finalizado";
    }

    @Override
    public boolean preparar(Pedido pedido) {
        return false;  // Can't go back to "Em Preparo"
    }

    @Override
    public boolean aguardar(Pedido pedido) {
        return false;  // Can't go back to "Em Espera"
    }

    @Override
    public boolean transportar(Pedido pedido) {
        return false;  // Can't go to "Em Transporte"
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;  // Can't go to "Cancelado"
    }

    @Override
    public boolean finalizar(Pedido pedido) {
        // Already "Finalizado", no state change
        return false;
    }
    public String toString() {
        return getEstado();  // This will return "Em Preparo"
    }

}
