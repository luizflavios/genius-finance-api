package br.com.genius_finance.controller.base;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.entity.BaseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
public abstract class BaseControllerImpl<T extends BaseDTO, D extends BaseResponseDTO, E extends BaseEntity> implements BaseController<T, D> {

    protected final BaseServiceImpl<T, D, E> baseService;
    protected final BaseMapper<T, D, E> baseMapper;

    protected BaseControllerImpl(BaseServiceImpl<T, D, E> baseService,
                                 BaseMapper<T, D, E> baseMapper) {
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<D> create(@RequestBody T t) {
        var response = baseService.save(t);
        return new ResponseEntity<>(baseMapper.toDTO(response), CREATED);
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<D> findById(@PathVariable UUID uuid) {
        var entity = baseService.findByUuid(uuid);
        return new ResponseEntity<>(baseMapper.toDTO(entity), OK);
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<Page<D>> findAll(@RequestParam int page, @RequestParam(defaultValue = "5", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var pageable = baseService.findAll(pageRequest);
        return new ResponseEntity<>(pageable, OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<D> update(@PathVariable UUID uuid, @RequestBody T t) {
        var entity = baseService.update(uuid, t);
        return new ResponseEntity<>(baseMapper.toDTO(entity), OK);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        baseService.delete(uuid);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
