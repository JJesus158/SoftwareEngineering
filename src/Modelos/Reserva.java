package Modelos;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {
    private Date data;
    private Socio socio;
    private Livro livro;

    public Reserva(Date data, Socio socio, Livro livro) {
        this.data = data;
        this.socio = socio;
        this.livro = livro;
    }

    public Date getData() {
        return data;
    }

    public Socio getSocio() {
        return socio;
    }

    public Livro getLivro() {
        return livro;
    }
}
