package br.edu.ifsul.cstsi.spring_trab.socio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SocioService {

    @Autowired
    private SocioRepository rep;

    public List<Socio> getSocio() {
        return rep.findAll();
    }

    public Socio getSocioById(Long id) {
        Optional<Socio> optional = rep.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Socio> getSocioByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }

    public Socio insert(Socio socio) {
        Assert.isNull(socio.getId(),"Não foi possível inserir o registro");
        return rep.save(socio);
    }

    public Socio update(Socio socio) {
        Assert.notNull(socio.getId(),"Não foi possível atualizar o registro");

        // Busca o produto no banco de dados
        Optional<Socio> optional = rep.findById(socio.getId());
        if(optional.isPresent()) {
            Socio db = optional.get();
            // Copiar as propriedades
            db.setNomeSocio(socio.getNomeSocio());
            db.setEndSocio(socio.getEndSocio());
            db.setTelSocio(socio.getTelSocio());
            db.setEmailSocial(socio.getEmailSocial());
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
