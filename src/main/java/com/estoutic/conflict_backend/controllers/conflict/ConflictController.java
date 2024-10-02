package com.estoutic.conflict_backend.controllers.conflict;

import com.estoutic.conflict_backend.services.conflict.impl.ConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conflict")
@RequiredArgsConstructor
public class ConflictController {

    private final ConflictService conflictService;

    @PostMapping("/create")
    public Integer createProof(){
        return conflictService.create();
    }
}
