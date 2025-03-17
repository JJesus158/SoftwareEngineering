import Enum.ContactoPredefinido;
import Enum.TipoSocio;
import Gestores.GestorRequisicoes;
import Modelos.Exemplar;
import Modelos.Fornecedor;
import Modelos.Livro;
import Modelos.Requisicao;
import Modelos.Socio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorRequisicoesTest {

    private GestorRequisicoes gestorRequisicoes;

    @BeforeEach
    void setUp() {
        gestorRequisicoes = new GestorRequisicoes();
    }

    private Requisicao criarRequisicao(LocalDate dataInicio) {
        Socio socio = new Socio("TesteInserir", 20, 39585910, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        Livro livro = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123456789));
        Exemplar exemplar = new Exemplar(livro);
        return new Requisicao(dataInicio,exemplar, socio);
    }

    @Test
    void testObterTodosAutores() {
        Requisicao requisicao = criarRequisicao(LocalDate.now());
        gestorRequisicoes.inserir(requisicao);

        Set<String> autores = gestorRequisicoes.obterTodosAutores();

        assertEquals(1, autores.size());
        assertTrue(autores.contains("autores"));
    }

    @Test
    void testMostrarTodasRequisicoes() {
        Requisicao requisicao = criarRequisicao(LocalDate.now());
        gestorRequisicoes.inserir(requisicao);

        LinkedList<Requisicao> requisicoes = gestorRequisicoes.mostrarTodasRequisicoes();

        assertEquals(1, requisicoes.size());
        assertEquals(requisicao, requisicoes.get(0));
    }

    @Test
    void testInserir() {
        Requisicao requisicao = criarRequisicao(LocalDate.now());
        gestorRequisicoes.inserir(requisicao);

        LinkedList<Requisicao> requisicoes = gestorRequisicoes.mostrarTodasRequisicoes();
        assertEquals(1, requisicoes.size());
        assertEquals(requisicao, requisicoes.get(0));
    }

    @Test
    void testMostrarRequisicoesPorCodigoDeSocio() {
        Requisicao requisicao = criarRequisicao(LocalDate.now());
        gestorRequisicoes.inserir(requisicao);

        LinkedList<Requisicao> requisicoes = gestorRequisicoes.mostrarRequisicoesPorCodigoDeSocio("1000");

        assertEquals(1, requisicoes.size());
        assertEquals(requisicao, requisicoes.get(0));
    }

    @Test
    void testMostrarRequisicoesPorNomeDoLivro() {
        Requisicao requisicao = criarRequisicao( LocalDate.now());
        gestorRequisicoes.inserir(requisicao);

        LinkedList<Requisicao> requisicoes = gestorRequisicoes.mostrarRequisicoesPorNomeDoLivro("titulo");

        assertEquals(1, requisicoes.size());
        assertEquals(requisicao, requisicoes.get(0));
    }


}
