package br.edu.ifsul.cstsi.spring_trab.dependente;

import br.edu.ifsul.cstsi.spring_trab.socio.Socio;
import br.edu.ifsul.cstsi.spring_trab.socio.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository rep;

    public List<Dependente> getDependente() {
        return rep.findAll();
    }

    public Dependente getDependenteById(Long id) {
        Optional<Dependente> optional = rep.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Dependente> getDependenteByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }

    public Dependente insert(Dependente dependente) {
        Assert.isNull(dependente.getCartaoDep(),"Não foi possível inserir o registro");
        return rep.save(dependente);
    }

    public Dependente update(Dependente dependente) {
        Assert.notNull(dependente.getSocioByCartaoSocio(),"Não foi possível atualizar o registro");

        // Busca o produto no banco de dados
        Optional<Dependente> optional = rep.findById(dependente.getCartaoDep());
        if(optional.isPresent()) {
            Dependente db = optional.get();
            // Copiar as propriedades
            db.setNomeDep(dependente.getNomeDep());
            db.setParentesco(dependente.getParentesco());
            db.setEmailDep(dependente.getEmailDep());

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
