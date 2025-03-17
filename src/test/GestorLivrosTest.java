import Gestores.GestorLivros;
import Modelos.Fornecedor;
import Modelos.Livro;
import Modelos.Socio;
import Enum.TipoSocio;
import Enum.ContactoPredefinido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorLivrosTest {
    @BeforeEach
    void setUp() {
        //prepara para criar um gestor novo para cada teste
        GestorLivros.setInstance(null);
    }

    @Test
    void testeInserirExemplar() {
        Livro livro = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123123123));
        GestorLivros.getInstance().criarExemplar(livro);
        assertTrue(livro.getExemplares().size() == 1 && GestorLivros.getInstance().getCodigos().size() == 1 &&
                livro.getExemplares().get(0).getCodigo() == GestorLivros.getInstance().getCodigos().get(0));
    }
    @Test
    void testeEfetuarReserva() {
        Livro livro = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123123123));
        livro.efetuarReserva(new Socio("", 0 ,0, 0, "", 0, "", TipoSocio.ALUNO, ContactoPredefinido.EMAIL));
        assertEquals(1, livro.getReservas().size());
    }

    @Test
    void testeInserirLivrosRepetidos() {
        Livro livro = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123123123));
        Livro livro2 = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123123123));
        GestorLivros.getInstance().inserir(livro);
        GestorLivros.getInstance().inserir(livro2);
        assertEquals(1, GestorLivros.getInstance().mostrarTodosLivros().size());
    }

    @Test
    void testeCarregarEGuardar() {
        GestorLivros gestor = GestorLivros.getInstance();
        gestor.guardar();

        try (FileInputStream fileIn = new FileInputStream("gestor_livros.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GestorLivros.setInstance((GestorLivros) in.readObject());
        } catch (IOException i) {
            GestorLivros.getInstance();
        } catch (ClassNotFoundException c) {
            System.out.println("GestorLivros class not found");
            c.printStackTrace();
            GestorLivros.getInstance();
        }

        assertEquals(gestor, GestorLivros.getInstance());
    }
    @Test
    void testeInserirLivro() {
        Livro livro = new Livro("autores", "titulo", "editora", "123123123", "genero", "subgenero", (short) 2020, (short) 2, (short) 2,(short) 2, (short) 2, new Fornecedor("fornecedor", 123123123));
        GestorLivros.getInstance().inserir(livro);
        assertTrue(GestorLivros.getInstance().mostrarTodosLivros().contains(livro));
    }

}