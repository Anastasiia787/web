package tech.itpark.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.web.dto.ManagerDto;
import tech.itpark.web.dto.ManagerUpdateDto;
import tech.itpark.web.manager.ManagerManager;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerManager manager;

    @GetMapping()
    public List<ManagerDto> getAll() {
        return manager.getAll();
    }

    @PostMapping()
    public ManagerUpdateDto save(@RequestBody ManagerUpdateDto dto) {
        return manager.create(dto);
    }


}
