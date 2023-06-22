package br.edu.ifsul.cstsi.spring_trab.categoria;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    @Query(value = "SELECT c FROM Categoria c where c.desCat like ?1")
    List<Categoria> findByNome(String nome);
}