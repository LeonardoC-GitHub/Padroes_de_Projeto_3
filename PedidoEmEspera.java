public class PedidoEmEspera extends PedidoEstado {

    private static final PedidoEmEspera instancia = new PedidoEmEspera();

    private PedidoEmEspera() {}

    public static PedidoEmEspera obterInstancia() {
        return instancia;
    }

    @Override
    public String getEstado() {
        return "Em Espera";
    }

    @Override
    public boolean preparar(Pedido pedido) {
        return false;  // Can't go back to "Em Preparo"
    }

    @Override
    public boolean aguardar(Pedido pedido) {
        // Already in "Em Espera", no state change
        return false;
    }

    @Override
    public boolean transportar(Pedido pedido) {
        pedido.setEstado(PedidoEmTransporte.obterInstancia());  // Transition to "Em Transporte"
        return true;
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
