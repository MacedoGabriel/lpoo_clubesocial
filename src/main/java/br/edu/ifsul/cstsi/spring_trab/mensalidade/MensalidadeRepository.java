package br.edu.ifsul.cstsi.spring_trab.mensalidade;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface MensalidadeRepository extends JpaRepository<Mensalidade,Long> {


    @Query(value = "SELECT c FROM Mensalidade c where c.quitMens = ?1")
    List<Mensalidade> findByQuitMens(byte quitMens);

    @Query(value="select p from Socio c inner join Mensalidade p on c.id = p.socioByCartaoSocio.id where c.id = ?1")
    List<Mensalidade> findByIdSocio(Long id);
}
