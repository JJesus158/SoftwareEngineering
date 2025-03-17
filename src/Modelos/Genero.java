package Modelos;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Esta classe é usada para verificar se um subgénero pertence a um género
 */

public enum Genero {

    INSTANCIA;

    private final LinkedList<String> generos;
    private final HashMap<String, LinkedList<String>> subGeneros;

    Genero() {
        subGeneros = new HashMap<>();

        generos = new LinkedList<>();

        generos.add("Ficção");
        generos.add("Não Ficção");
        generos.add("Poesia");
        generos.add("Drama");

        LinkedList<String> ficcao = new LinkedList<>();
        ficcao.add("Ficção Científica");
        ficcao.add("Fantasia");
        ficcao.add("Romance");
        ficcao.add("Mistério");
        ficcao.add("Terror");
        ficcao.add("Aventura");
        ficcao.add("Literatura Infantojuvenil");

        LinkedList<String> naoFiccao = new LinkedList<>();
        naoFiccao.add("Biografia");
        naoFiccao.add("Autoajuda");
        naoFiccao.add("História");
        naoFiccao.add("Ciências Sociais");
        naoFiccao.add("Negócios");
        naoFiccao.add("Ciências");
        naoFiccao.add("Gastronomia");

        LinkedList<String> poesia = new LinkedList<>();
        poesia.add("Poesia Lírica");
        poesia.add("Poesia Dramática");
        poesia.add("Poesia Narrativa");

        LinkedList<String> drama = new LinkedList<>();
        drama.add("Drama Familiar");
        drama.add("Drama Urbano");
        drama.add("Drama Rural");


        subGeneros.put("Ficção", ficcao);
        subGeneros.put("Não Ficção", naoFiccao);
        subGeneros.put("Poesia", poesia);
        subGeneros.put("Drama", drama);
    }

    public LinkedList<String> getGeneros()
    {
        return new LinkedList<>(generos);
    }

    public LinkedList<String> getSubgenerosPorGenero(String genero)
    {
        LinkedList<String> subgeneros = subGeneros.get(genero);
        return subgeneros != null ? new LinkedList<>(subgeneros) : new LinkedList<>();
    }
}
