package com.reservemicroservice.reservemicroservice.services;

import com.reservemicroservice.reservemicroservice.dtos.ReserveDTO;
import com.reservemicroservice.reservemicroservice.dtos.SavedReserveDTO;
import com.reservemicroservice.reservemicroservice.entities.Reserve;
import com.reservemicroservice.reservemicroservice.repositories.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    ReserveRepository reserveRepository;

    public String saveReserve(ReserveDTO reservationDTO) {
        Reserve reserve = new Reserve();
        reserve.setName(reservationDTO.getName());
        reserve.setEmail(reservationDTO.getEmail());
        reserve.setDocType(reservationDTO.getDocType());
        reserve.setDocNumber(reservationDTO.getDocNumber());
        reserve.setReserveDate(reservationDTO.getReserveDate());
        reserve.setReserveType(reservationDTO.getReserveType());
        reserve.setPeople(reservationDTO.getPeople());
        reserve.setObservations(reservationDTO.getObservations());

        reserveRepository.save(reserve);
        return "Reservación en espera de ser aprobada";
    }

    public List<SavedReserveDTO> getAllReserves() {
        List<Reserve> reserves = reserveRepository.findAll();
        List<SavedReserveDTO> savedReserveDTOs = new ArrayList<>();

        for (Reserve reserve : reserves) {
            SavedReserveDTO savedReserveDTO = dtoFromEntity(reserve);
            savedReserveDTOs.add(savedReserveDTO);
        }

        return savedReserveDTOs;
    }


    public SavedReserveDTO updateReserve(Long id, ReserveDTO reserveDto) {
        Reserve existingReserve = reserveRepository.findById(id).orElse(null);
        if (existingReserve == null)
            return null;

        existingReserve.setName(reserveDto.getName());
        existingReserve.setEmail(reserveDto.getEmail());
        existingReserve.setDocType(reserveDto.getDocType());
        existingReserve.setDocNumber(reserveDto.getDocNumber());
        existingReserve.setReserveDate(reserveDto.getReserveDate());
        existingReserve.setReserveType(reserveDto.getReserveType());
        existingReserve.setPeople(reserveDto.getPeople());
        existingReserve.setObservations(reserveDto.getObservations());

        Reserve updatedReserve = reserveRepository.save(existingReserve);
        return dtoFromEntity(updatedReserve);
    }


    private SavedReserveDTO dtoFromEntity(Reserve reserve) {
        SavedReserveDTO savedReserveDTO = new SavedReserveDTO();

        savedReserveDTO.setId(reserve.getId());
        savedReserveDTO.setName(reserve.getName());
        savedReserveDTO.setEmail(reserve.getEmail());
        savedReserveDTO.setDocType(reserve.getDocType());
        savedReserveDTO.setDocNumber(reserve.getDocNumber());
        savedReserveDTO.setReserveDate(reserve.getReserveDate());
        savedReserveDTO.setReserveType(reserve.getReserveType());
        savedReserveDTO.setPeople(reserve.getPeople());
        savedReserveDTO.setObservations(reserve.getObservations());

        return savedReserveDTO;
    }
}
