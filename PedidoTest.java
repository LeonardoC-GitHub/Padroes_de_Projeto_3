import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
    }

    @Test
    public void testEstadoInicial() {
        assertEquals("Em Preparo", pedido.getEstado(), "Estado inicial deve ser 'Em Preparo'");
    }

    @Test
    public void testTransicaoParaEmEspera() {
        pedido.aguardarTransporte();
        assertEquals("Em Espera", pedido.getEstado().getEstado(), "Após aguardar, estado deve ser 'Em Espera'");
    }

    @Test
    public void testTransicaoParaEmTransporte() {
        pedido.aguardarTransporte();  // Primeiro, vai para "Em Espera"
        pedido.transportar();
        assertEquals("Em Transporte", pedido.getEstado().getEstado(), "Após transporte, estado deve ser 'Em Transporte'");
    }

    @Test
    public void testTransicaoParaFinalizado() {
        pedido.aguardarTransporte();  // Primeiro, vai para "Em Espera"
        pedido.transportar();         // Depois, vai para "Em Transporte"
        pedido.finalizarPedido();
        assertEquals("Finalizado", pedido.getEstado().getEstado(), "Após finalizar, estado deve ser 'Finalizado'");
    }

    @Test
    public void testTransicaoParaCancelado() {
        pedido.cancelar();
        assertEquals("Cancelado", pedido.getEstado().getEstado(), "Após cancelar, estado deve ser 'Cancelado'");
    }

    @Test
    public void testTransicaoInvalidaDeEmPreparo() {
        // Estado inicial é "Em Preparo"
        assertFalse(pedido.preparar(), "Não pode preparar um pedido que já está em 'Em Preparo'");
    }

    @Test
    public void testTransicaoInvalidaDeCancelado() {
        pedido.cancelar();  // Transição para "Cancelado"
        assertFalse(pedido.preparar(), "Não pode preparar um pedido que já foi 'Cancelado'");
        assertFalse(pedido.transportar(), "Não pode transportar um pedido que foi 'Cancelado'");
    }
}
