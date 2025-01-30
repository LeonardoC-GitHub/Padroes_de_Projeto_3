public class EmEspera extends Estado {

    private static final EmEspera instancia = new EmEspera();

    private EmEspera() {}

    public static EmEspera obterInstancia() {
        return instancia;
    }

    @Override
    public String obterNomeEstado() {
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
        pedido.setEstado(EmTransporte.obterInstancia());  // Transition to "Em Transporte"
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
