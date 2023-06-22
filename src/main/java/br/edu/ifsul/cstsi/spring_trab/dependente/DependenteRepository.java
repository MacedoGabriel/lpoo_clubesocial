package br.edu.ifsul.cstsi.spring_trab.dependente;

import br.edu.ifsul.cstsi.spring_trab.dependente.Dependente;
import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DependenteRepository extends JpaRepository<Dependente,Long> {

    @Query(value = "SELECT d FROM Dependente d where d.nomeDep like ?1")
    List<Dependente> findByNome(String nome);

}
