package br.edu.ifsul.cstsi.spring_trab.dependente;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dependente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cartao_dep")
    private Long cartaoDep;
    @Basic
    @Column(name = "nome_dep")
    private String nomeDep;
    @Basic
    @Column(name = "parentesco")
    private String parentesco;
    @Basic
    @Column(name = "email_dep")
    private String emailDep;
    //@Basic
    //@Column(name = "cartao_socio")
    //private Long cartaoSocio;
    @ManyToOne
    @JoinColumn(name = "cartao_socio", referencedColumnName = "cartao_socio", nullable = false)
    private Socio socioByCartaoSocio;

   }
