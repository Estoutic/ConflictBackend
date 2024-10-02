package com.estoutic.conflict_backend.controllers.conflict;


import com.estoutic.conflict_backend.dto.ProofDto;
import com.estoutic.conflict_backend.services.conflict.impl.ProofService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/proof")
@RequiredArgsConstructor
public class ProofController {

    private final ProofService proofService;

    @PostMapping("/create")
    public UUID createProof(@RequestBody ProofDto proofDto){
        return proofService.add(proofDto);
    }


}
