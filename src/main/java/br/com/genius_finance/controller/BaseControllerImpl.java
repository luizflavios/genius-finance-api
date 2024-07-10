package br.com.genius_finance.controller;

import br.com.genius_finance.model.dto.BaseDTO;
import br.com.genius_finance.model.entity.BaseEntity;
import br.com.genius_finance.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
public class BaseControllerImpl<T extends BaseDTO, D extends BaseDTO, E extends BaseEntity> implements BaseController<T, D> {

    private final BaseService<E> baseService;

    @Override
    @PostMapping
    public ResponseEntity<D> create(@RequestBody T t) {
        return new ResponseEntity<>(null, CREATED);
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<D> findById(@PathVariable UUID uuid) {
        return new ResponseEntity<>(null, OK);
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<Page<D>> findAll(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(null, OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<D> update(@PathVariable UUID uuid, @RequestBody T t) {
        return new ResponseEntity<>(null, OK);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        return new ResponseEntity<>(NO_CONTENT);
    }
}
