public class EmPreparo extends Estado {

    private static final EmPreparo instancia = new EmPreparo();

    private EmPreparo() {}

    public static EmPreparo obterInstancia() {
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
        pedido.setEstado(EmEspera.obterInstancia());  // Transition to "Em Espera"
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(Cancelado.obterInstancia());  // Transition to "Cancelado"
        return true;
    }

    @Override
    public boolean finalizar(Pedido pedido) {
        pedido.setEstado(Finalizado.obterInstancia());  // Transition to "Finalizado"
        return true;
    }
}
