package com.restApi.prueba.resources;

import com.restApi.prueba.http_errors.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(ReactiveExceptionResource.REACTIVE_EXCEPTIONS)
public class ReactiveExceptionResource {
    public static final String REACTIVE_EXCEPTIONS = "/reactive-exceptions";
    public static final String ID_ID = "/{id}";

    @GetMapping(ID_ID)
    public ValidatedDto read(@PathVariable int id) {
        if (id < 1) {
            throw new NotFoundException("id:" + id);
        }
        return new ValidatedDto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
    }

    @PostMapping
    public ValidatedDto create(@Valid @RequestBody ValidatedDto dto) {
        return dto;
    }
}
