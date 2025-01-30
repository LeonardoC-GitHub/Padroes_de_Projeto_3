public class Pedido {
    private Estado estado;

    public Pedido() {
        this.estado = EmPreparo.obterInstancia();  // Initial state is "Em Preparo"
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void manipular() {
        System.out.println("Pedido está no estado: " + estado.obterNomeEstado());
    }

    public boolean preparar() {
        return estado.preparar(this);
    }

    public boolean aguardarTransporte() {
        return estado.aguardar(this);
    }

    public boolean transportar() {
        return estado.transportar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public boolean finalizarPedido() {
        return estado.finalizar(this);
    }
}
