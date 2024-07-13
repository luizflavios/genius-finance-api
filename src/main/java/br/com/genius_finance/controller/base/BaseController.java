package br.com.genius_finance.controller.base;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;


public interface BaseController<T extends BaseDTO, D extends BaseResponseDTO> {

    ResponseEntity<D> create(@RequestBody T t);

    ResponseEntity<D> findById(@PathVariable UUID uuid);

    ResponseEntity<Page<D>> findAll(@RequestParam int page, @RequestParam int size);

    ResponseEntity<D> update(@PathVariable UUID uuid, @RequestBody T t);

    ResponseEntity<Void> delete(@PathVariable UUID uuid);

}

