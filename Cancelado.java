public class Cancelado extends Estado {

    private static final Cancelado instancia = new Cancelado();

    private Cancelado() {}

    public static Cancelado obterInstancia() {
        return instancia;
    }

    @Override
    public String obterNomeEstado() {
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
        pedido.setEstado(Finalizado.obterInstancia());  // Transition to "Finalizado"
        return true;
    }
}
