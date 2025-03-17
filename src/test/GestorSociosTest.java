import Enum.ContactoPredefinido;
import Enum.TipoSocio;
import Gestores.GestorLivros;
import Gestores.GestorSocios;
import Modelos.Fornecedor;
import Modelos.Livro;
import Modelos.Socio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorSociosTest {
    @BeforeEach
    void setUp() {
        GestorSocios.setInstance(null);
    }

    @Test
    void testeInserirSocio() {
        Socio socio = new Socio("TesteInserir", 20, 39585910, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio);
        assertTrue(GestorSocios.getInstance().mostrarTodosSocios().contains(socio));
    }

    @Test
    void testeEditarSocio() {
        Socio socio = new Socio("Teste", 20, 39585910, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio);

        socio.editarSocio("TesteEditar", 20, 39585910, 123456789, "editar@mail.com", 999999999, ContactoPredefinido.EMAIL, "Pocariça", TipoSocio.PROFESSOR);

        assertEquals("TesteEditar", socio.getNome());
        assertEquals(20, socio.getIdade());
        assertEquals(39585910, socio.getCC());
        assertEquals(123456789, socio.getNif());
        assertEquals("editar@mail.com", socio.getEmail());
        assertEquals(999999999, socio.getTelefone());
        assertEquals("Pocariça", socio.getMorada());
        assertEquals(TipoSocio.PROFESSOR, socio.getTipo());
        assertEquals(ContactoPredefinido.EMAIL, socio.getContactoPredefinido());
        assertTrue(GestorSocios.getInstance().mostrarTodosSocios().contains(socio));
    }

    @Test
    void testeRemoverSocio() {
        Socio socio = new Socio("TesteRemover", 20, 39585910, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio);

        GestorSocios.getInstance().remover(socio);
        assertFalse(GestorSocios.getInstance().mostrarTodosSocios().contains(socio));
    }

    @Test
    void testePagarAnuidade() {
        Socio socio = new Socio("Teste", 30, 12345678, 123456789, "teste@mail.com", 987654321, "Morada", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio);

        Date dataAntiga = socio.getExpiracaoAnuidade();
        socio.pagarAnuidade();
        Date dataNova = socio.getExpiracaoAnuidade();

        assertNotEquals(dataAntiga, dataNova);
    }

    @Test
    void testeInserirSociosNifIgual() {
        Socio socio1 = new Socio("TesteInserir", 20, 39585910, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio1);
        Socio socio2 = new Socio("TesteInserir", 20, 12345678, 123456789, "mail.com", 999999999, "Pocariça", TipoSocio.ALUNO, ContactoPredefinido.EMAIL);
        GestorSocios.getInstance().inserir(socio2);

        assertEquals(1, GestorSocios.getInstance().mostrarTodosSocios().size());
    }

}