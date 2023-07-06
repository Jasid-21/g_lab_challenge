package com.reservemicroservice.reservemicroservice.services;

import com.reservemicroservice.reservemicroservice.dtos.ReserveDto;
import com.reservemicroservice.reservemicroservice.entities.Reserve;
import com.reservemicroservice.reservemicroservice.repositories.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {
    @Autowired
    ReserveRepository reserveRepository;

    public String saveReserve(ReserveDto reservationDTO) {
        Reserve reserve = new Reserve();
        reserve.setName(reservationDTO.getName());
        reserve.setEmail(reservationDTO.getEmail());
        reserve.setDocType(reservationDTO.getDocType());
        reserve.setDocNumber(reservationDTO.getDocNumber());
        reserve.setReserveDate(reservationDTO.getReserveDate());
        reserve.setReserveType(reservationDTO.getEventType());
        reserve.setPeople(reservationDTO.getPeople());
        reserve.setObservations(reservationDTO.getObservations());

        reserveRepository.save(reserve);
        return "Reservación en espera de ser aprobada";
    }
}
