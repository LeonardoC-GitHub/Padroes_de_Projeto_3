public class PedidoCancelado extends PedidoEstado {

    private static final PedidoCancelado instancia = new PedidoCancelado();

    private PedidoCancelado() {}

    public static PedidoCancelado obterInstancia() {
        return instancia;
    }

    @Override
    public String getEstado() {
        return "Cancelado";
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
        // Already in "Cancelado", no state change
        return false;
    }

    @Override
    public boolean finalizar(Pedido pedido) {
        pedido.setEstado(PedidoFinalizado.obterInstancia());  // Transition to "Finalizado"
        return true;
    }
}
