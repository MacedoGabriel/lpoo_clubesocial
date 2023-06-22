package br.edu.ifsul.cstsi.spring_trab.mensalidade;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mensalidade {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero_mens")
    private Long numeroMens;
    //@Basic
    //@Column(name = "cartao_socio")
    //private Long cartaoSocio;
    @Basic
    @Column(name = "data_mens")
    private LocalDate dataMens;
    @Basic
    @Column(name = "data_pgto_mens")
    private LocalDate dataPgtoMens;
    @Basic
    @Column(name = "valor_pago")
    private Double valorPago;
    @Basic
    @Column(name = "valor_mens")
    private Double valorMens;
    @Basic
    @Column(name = "juros_mens")
    private Double jurosMens;
    @Basic
    @Column(name = "quit_mens")
    private Byte quitMens;
    @ManyToOne
    @JoinColumn(name = "cartao_socio", referencedColumnName = "cartao_socio", nullable = false)
    private Socio socioByCartaoSocio;

}
