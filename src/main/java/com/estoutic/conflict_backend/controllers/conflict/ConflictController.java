package com.estoutic.conflict_backend.controllers.conflict;

import com.estoutic.conflict_backend.dto.ConflictDto;
import com.estoutic.conflict_backend.services.conflict.impl.ConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conflict")
@RequiredArgsConstructor
public class ConflictController {

    private final ConflictService conflictService;

    @PostMapping("/create")
    public UUID createProof(){
        return conflictService.create();
    }

    @GetMapping("/all")
    public List<ConflictDto> getAllConflicts(){
        return conflictService.getAll();
    }
}
