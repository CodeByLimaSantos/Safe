package com.limasoftware.Safety.service;


import com.limasoftware.Safety.dto.SchedulingCreateRequest;
import com.limasoftware.Safety.dto.SchedulingResponse;
import com.limasoftware.Safety.dto.SchedulingUpdateRequest;
import com.limasoftware.Safety.enums.SchedulingStatus;
import com.limasoftware.Safety.mapper.SchedulingMapper;
import com.limasoftware.Safety.model.Scheduling;
import com.limasoftware.Safety.repository.SchedulingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;


    public SchedulingService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }







    ///create
    @Transactional
    public SchedulingResponse create(@Valid SchedulingCreateRequest request) {

        validateIntervalo(request.startDate(), request.endDate());
        verifyConflict(request.client(), request.startDate(), request.endDate(), null);



        Scheduling entity = SchedulingMapper.toEntity(request);
        entity = schedulingRepository.save(entity);
        return SchedulingMapper.toResponse(entity);

    }









    /// Update
    @Transactional
    public SchedulingResponse update(Long id, @Valid SchedulingUpdateRequest request) {
        Scheduling entity = schedulingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));

        SchedulingMapper.merge(entity, request);

        validateIntervalo(request.startDate(), request.endDate());
        verifyConflict(entity.getClient(), request.startDate(), request.endDate(), entity.getId());


        entity = schedulingRepository.save(entity);
        return SchedulingMapper.toResponse(entity);
    }







    /// DELETE
    @Transactional
    public SchedulingResponse delete(Long id) {
        Scheduling entity = schedulingRepository.findById(id)

                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));

        entity.setStatus(SchedulingStatus.CANCELED);

        entity = schedulingRepository.save(entity);

        return SchedulingMapper.toResponse(entity);
    }






    @Transactional
    public SchedulingResponse finish(Long id) {
        Scheduling entity = schedulingRepository.findById(id)

                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));

        entity.setStatus(SchedulingStatus.COMPLETED);

        entity = schedulingRepository.save(entity);

        return SchedulingMapper.toResponse(entity);


    }






    public SchedulingResponse searchById(Long id) {
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        return SchedulingMapper.toResponse(scheduling);
    }





    public List<SchedulingResponse> findAll() {
        return schedulingRepository.findAll()
                .stream()
                .map(SchedulingMapper::toResponse)
                .toList();
    }







    /// methods
    private void validateIntervalo(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Intervalo Inválido! Data de início deve ser anterior a data final!");

        }
    }


    private void verifyConflict(String client, LocalDateTime start, LocalDateTime end, Long id) {
        if (schedulingRepository.existsConflict(client, start, end, id)) {
            throw new IllegalArgumentException("Conflito na agenda: Horário desejado em conflito com outro agendamento");
        }


    }




}