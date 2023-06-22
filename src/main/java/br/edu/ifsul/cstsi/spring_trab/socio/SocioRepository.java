package br.edu.ifsul.cstsi.spring_trab.socio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio,Long> {

    @Query(value = "SELECT c FROM Socio c where c.nomeSocio like ?1")
    List<Socio> findByNome(String nome);

}