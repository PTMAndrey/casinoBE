package com.usv.casino.service;

import com.usv.casino.dto.BeneficiuDTO;
import com.usv.casino.entity.Beneficiu;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.BeneficiuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BeneficiuService {

    public static final String MESAJ_DE_EROARE = "Nu exista beneficiu";

    public final BeneficiuRepository beneficiuRepository;

    public BeneficiuService(BeneficiuRepository beneficiuRepository) {
        this.beneficiuRepository = beneficiuRepository;
    }

    public List<Beneficiu> getBeneficiu(){
        Iterable<Beneficiu> iterableBeneficiu = beneficiuRepository.findAll();
        List<Beneficiu> beneficiue= new ArrayList<>();

        iterableBeneficiu.forEach(beneficiu ->
                beneficiue.add(Beneficiu.builder()
                                .idBeneficiu(beneficiu.getIdBeneficiu())
                                .procentDepunere(beneficiu.getProcentDepunere())
                                .bani(beneficiu.getBani())
                                .bonusuri(beneficiu.getBonusuri())
                        .build()));
        return beneficiue;
    }

    public Beneficiu getBeneficiuDupaId(UUID id){
        return beneficiuRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });

    }

    public Beneficiu adaugaBeneficiu(BeneficiuDTO beneficiu){
        Beneficiu beneficiu1=Beneficiu.builder()
                .procentDepunere(beneficiu.getProcentDepunere())
                .bani(beneficiu.getBani())
                .build();

        beneficiuRepository.save(beneficiu1);
        return beneficiu1;
    }

    public Beneficiu actualizareBeneficiu (UUID id, BeneficiuDTO beneficiu) {
        Beneficiu beneficiuExistent = beneficiuRepository.findById(id).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE));

        beneficiuExistent.setProcentDepunere(beneficiu.getProcentDepunere());
        beneficiuExistent.setBani(beneficiu.getBani());

        return beneficiuRepository.save(beneficiuExistent);
    }

    public void stergeBeneficiu (UUID id) {
        Beneficiu beneficiuExistent = beneficiuRepository.findById(id).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE));

        beneficiuRepository.delete(beneficiuExistent);
    }

}
