public class PedidoEmTransporte extends PedidoEstado {

    private static final PedidoEmTransporte instancia = new PedidoEmTransporte();

    private PedidoEmTransporte() {}

    public static PedidoEmTransporte obterInstancia() {
        return instancia;
    }

    @Override
    public String getEstado() {
        return "Em Transporte";
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
        // Already in "Em Transporte", no state change
        return false;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoCancelado.obterInstancia());  // Transition to "Cancelado"
        return true;
    }

    @Override
    public boolean finalizar(Pedido pedido) {
        pedido.setEstado(PedidoFinalizado.obterInstancia());  // Transition to "Finalizado"
        return true;
    }
    public String toString() {
        return getEstado();  // This will return "Em Preparo"
    }

}
