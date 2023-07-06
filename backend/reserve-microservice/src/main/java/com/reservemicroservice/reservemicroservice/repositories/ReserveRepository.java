package com.reservemicroservice.reservemicroservice.repositories;

import com.reservemicroservice.reservemicroservice.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> { }
