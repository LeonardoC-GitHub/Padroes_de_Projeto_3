public abstract class Estado {
    
    // Returns the name of the current state (Em Preparo, Em Espera, etc.)
    public abstract String getEstado();

    // Default actions for each state, can be overridden by concrete states
    public boolean preparar(Pedido pedido) {
        return false;
    }

    public boolean aguardar(Pedido pedido) {
        return false;
    }

    public boolean transportar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        return false;
    }

    public boolean finalizar(Pedido pedido) {
        return false;
    }
}
