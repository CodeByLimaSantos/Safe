package com.limasoftware.Safety.controller;

import com.limasoftware.Safety.dto.SchedulingCreateRequest;
import com.limasoftware.Safety.dto.SchedulingResponse;
import com.limasoftware.Safety.dto.SchedulingUpdateRequest;
import com.limasoftware.Safety.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Agendamentos")
public class SchedulingController {


    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    private final SchedulingService schedulingService;


    @PostMapping("/criar")
    public SchedulingResponse create(@Valid @RequestBody SchedulingCreateRequest request) {

        return schedulingService.create(request);

    }


    @PostMapping("/{id}/editar")
    public SchedulingResponse update(@PathVariable Long id, @Valid @RequestBody SchedulingUpdateRequest request) {

        return schedulingService.update(id, request);


    }


    @PostMapping("/{id}/excluir")
    public SchedulingResponse delete(@PathVariable Long id) {

        return schedulingService.delete(id);

    }


    @PutMapping("/{id}/concluir")
    public SchedulingResponse completed(@PathVariable Long id) {

        return schedulingService.finish(id);

    }


    @GetMapping("/{id}")
    public SchedulingResponse searchById(@PathVariable Long id) {

        return schedulingService.searchById(id);

    }


    @GetMapping("/ativos")
    public ResponseEntity<List<SchedulingResponse>> findAll() {
        List<SchedulingResponse> response = schedulingService.findAll();
        return ResponseEntity.ok(response);

    }




}

