package br.edu.ifsul.cstsi.spring_trab.categoria;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Categoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "des_cat")
    private String desCat;
    //@OneToMany(mappedBy = "categoriaByCategoria")
    //private Collection<Socio> sociosById;
}
