public class EmTransporte extends Estado {

    private static final EmTransporte instancia = new EmTransporte();

    private EmTransporte() {}

    public static EmTransporte obterInstancia() {
        return instancia;
    }

    @Override
    public String obterNomeEstado() {
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
        pedido.setEstado(Cancelado.obterInstancia());  // Transition to "Cancelado"
        return true;
    }

    @Override
    public boolean finalizar(Pedido pedido) {
        pedido.setEstado(Finalizado.obterInstancia());  // Transition to "Finalizado"
        return true;
    }
}
