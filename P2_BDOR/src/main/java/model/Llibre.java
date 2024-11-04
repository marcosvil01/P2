package model;

import javax.persistence.*;

@Entity
@Table(name = "llibres")
public class Llibre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titol")
    private String titol;

    @Column(name = "autor")
    private String autor;

    @Column(name = "isbn")
    private String isbn;

    public Llibre() {
    }

    public Llibre(String titol, String autor, String isbn) {
        this.titol = titol;
        this.autor = autor;
        this.isbn = isbn;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Llibre [id=" + id + ", titol=" + titol + ", autor=" + autor + ", isbn=" + isbn + "]";
    }
}
