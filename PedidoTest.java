import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PedidoTest {

    private Pedido pedido;
    private Cliente cliente1;
    private Cliente cliente2;
    private List<String> notificacoes;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
        
        // Criando uma lista para armazenar notificações simuladas
        notificacoes = new ArrayList<>();
        
        // Criando clientes e modificando o método de notificação para armazenar mensagens
        cliente1 = new Cliente("João") {
            @Override
            public void update(java.util.Observable o, Object arg) {
                notificacoes.add("Cliente João foi notificado. Estado do pedido: " + pedido.getEstado().getEstado());
            }
        };

        cliente2 = new Cliente("Maria") {
            @Override
            public void update(java.util.Observable o, Object arg) {
                notificacoes.add("Cliente Maria foi notificado. Estado do pedido: " + pedido.getEstado().getEstado());
            }
        };

        // Adicionando os clientes como observadores
        pedido.addObserver(cliente1);
        pedido.addObserver(cliente2);
    }

    @Test
    public void testEstadoInicial() {
        assertEquals("Em Preparo", pedido.getEstado().getEstado(), "Estado inicial deve ser 'Em Preparo'");
    }

    @Test
    public void testTransicaoParaEmEspera() {
        pedido.aguardarTransporte();
        assertEquals("Em Espera", pedido.getEstado().getEstado(), "Após aguardar, estado deve ser 'Em Espera'");
        assertEquals(2, notificacoes.size(), "Os dois clientes devem ter sido notificados");
    }

    @Test
    public void testTransicaoParaEmTransporte() {
        pedido.aguardarTransporte();  
        pedido.transportar();
        assertEquals("Em Transporte", pedido.getEstado().getEstado(), "Após transporte, estado deve ser 'Em Transporte'");
        assertTrue(notificacoes.contains("Cliente João foi notificado. Estado do pedido: Em Transporte"));
        assertTrue(notificacoes.contains("Cliente Maria foi notificado. Estado do pedido: Em Transporte"));
    }

    @Test
    public void testTransicaoParaFinalizado() {
        pedido.aguardarTransporte();  
        pedido.transportar();         
        pedido.finalizarPedido();
        assertEquals("Finalizado", pedido.getEstado().getEstado(), "Após finalizar, estado deve ser 'Finalizado'");
        assertTrue(notificacoes.contains("Cliente João foi notificado. Estado do pedido: Finalizado"));
        assertTrue(notificacoes.contains("Cliente Maria foi notificado. Estado do pedido: Finalizado"));
    }

    @Test
    public void testTransicaoParaCancelado() {
        pedido.cancelar();
        assertEquals("Cancelado", pedido.getEstado().getEstado(), "Após cancelar, estado deve ser 'Cancelado'");
        assertTrue(notificacoes.contains("Cliente João foi notificado. Estado do pedido: Cancelado"));
        assertTrue(notificacoes.contains("Cliente Maria foi notificado. Estado do pedido: Cancelado"));
    }

    @Test
    public void testRemoverObservadorNaoRecebeNotificacao() {
        pedido.addObserver(cliente2);
        pedido.setEstado(PedidoEmTransporte.obterInstancia());

        assertFalse(notificacoes.contains("Cliente Maria foi notificado. Estado do pedido: Transportado"),
                "Cliente Maria não deve ser notificado após ser removido");
    }

    @Test
    public void testAdicionarObservadorRecebeNotificacao() {
        Cliente cliente3 = new Cliente("Carlos") {
            @Override
            public void update(java.util.Observable o, Object arg) {
                notificacoes.add("Cliente Carlos foi notificado. Estado do pedido: " + pedido.getEstado().getEstado());
            }
        };

        pedido.addObserver(cliente3);
        pedido.setEstado(PedidoFinalizado.obterInstancia());

        assertTrue(notificacoes.contains("Cliente Carlos foi notificado. Estado do pedido: Finalizado"),
                "Cliente Carlos deve ser notificado após ser adicionado");
    }
}
