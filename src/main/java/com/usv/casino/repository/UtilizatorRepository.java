package com.usv.casino.repository;

import com.usv.casino.entity.Utilizator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtilizatorRepository extends CrudRepository<Utilizator, UUID> {

    Optional<Utilizator> findByCodulMeuReferal(String codulMeuReferal);
}