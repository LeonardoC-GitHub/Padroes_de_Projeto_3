public class PedidoEmPreparo extends PedidoEstado {

    private static final PedidoEmPreparo instancia = new PedidoEmPreparo();

    private PedidoEmPreparo() {}

    public static PedidoEmPreparo obterInstancia() {
        return instancia;
    }

    @Override
    public String getEstado() {
        return "Em Preparo";
    }

    @Override
    public boolean preparar(Pedido pedido) {
        // Already in "Em Preparo", no state change
        return false;
    }

    @Override
    public boolean aguardar(Pedido pedido) {
        pedido.setEstado(PedidoEmEspera.obterInstancia());  // Transition to "Em Espera"
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
