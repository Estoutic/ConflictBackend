package com.estoutic.conflict_backend.services.conflict;

import com.estoutic.conflict_backend.dto.ConflictDto;

import java.util.List;
import java.util.UUID;

public interface IConflictService {

    UUID create();

    List<ConflictDto> getAll();
}
