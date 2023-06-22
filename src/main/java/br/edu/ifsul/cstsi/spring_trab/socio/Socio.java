package br.edu.ifsul.cstsi.spring_trab.socio;

import br.edu.ifsul.cstsi.spring_trab.categoria.Categoria;
import br.edu.ifsul.cstsi.spring_trab.dependente.Dependente;
import br.edu.ifsul.cstsi.spring_trab.mensalidade.Mensalidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Socio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cartao_socio")
    private Long id;
    //@Basic
    //@Column(name = "categoria")
    //private Long categoria;
    @Basic
    @Column(name = "nome_socio")
    private String nomeSocio;
    @Basic
    @Column(name = "end_socio")
    private String endSocio;
    @Basic
    @Column(name = "tel_socio")
    private String telSocio;
    @Basic
    @Column(name = "email_social")
    private String emailSocial;
    @OneToMany(mappedBy = "socioByCartaoSocio")
    private Collection<Dependente> dependentesByCartaoSocio;
    @OneToMany(mappedBy = "socioByCartaoSocio")
    private Collection<Mensalidade> mensalidadesByCartaoSocio;
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoriaByCategoria;

    @Override
    public String toString() {
        return "\nSocio{" +
                "id=" + id +
                ", nomeSocio='" + nomeSocio + '\'' +
                ", endSocio='" + endSocio + '\'' +
                ", telSocio='" + telSocio + '\'' +
                ", emailSocial='" + emailSocial + '\'' +
                ", categoriaByCategoria=" + categoriaByCategoria +
                '}';
    }
}
