package br.edu.ifsul.cstsi.spring_trab.mensalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository rep;

    public List<Mensalidade> getMensalidade() {
        return rep.findAll();
    }

    public Mensalidade getMensalidadeById(Long id) {
        Optional<Mensalidade> optional = rep.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Mensalidade> getMensalidadeByQuit(byte quit){
        return new ArrayList<>(rep.findByQuitMens(quit));
    }

    public List<Mensalidade> getMensalidadeByIdSocio(Long id){
        Assert.notNull(id,"Não foi possível buscar o registro");
        return rep.findByIdSocio(id);
    }

    public Mensalidade insert(Mensalidade mensalidade) {
        Assert.isNull(mensalidade.getNumeroMens(),"Não foi possível inserir o registro");
        return rep.save(mensalidade);
    }

    public Mensalidade update(Mensalidade mensalidade) {
        Assert.notNull(mensalidade.getNumeroMens(),"Não foi possível atualizar o registro");


        Optional<Mensalidade> optional = rep.findById(mensalidade.getNumeroMens());
        if(optional.isPresent()) {
            Mensalidade db = optional.get();
            // Copiar as propriedades
            db.setDataMens(mensalidade.getDataMens());
            db.setDataPgtoMens(mensalidade.getDataPgtoMens());
            db.setQuitMens(mensalidade.getQuitMens());
            db.setValorPago(mensalidade.getValorPago());
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
