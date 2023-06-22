package br.edu.ifsul.cstsi.spring_trab.categoria;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import br.edu.ifsul.cstsi.spring_trab.socio.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository rep;

    public List<Categoria> getCategoria() { return rep.findAll(); }

    public Categoria getCategortiaById(Long id) {
        Optional<Categoria> optional = rep.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Categoria> getCategoriaByNome(String nome) {return new ArrayList<>(rep.findByNome(nome + "%")); }

    public Categoria insert(Categoria categoria) {
        Assert.isNull(categoria.getId(),"Não foi possível inserir o registro");
        return rep.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Assert.notNull(categoria.getId(),"Não foi possível atualizar o registro");

        // Busca o produto no banco de dados
        Optional<Categoria> optional = rep.findById(categoria.getId());
        if(optional.isPresent()) {
            Categoria db = optional.get();
            // Copiar as propriedades
            db.setDesCat(categoria.getDesCat());
            // Atualiza o produto
            rep.save(db);

            return db;
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
