package br.com.genius_finance.service.base;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.entity.base.BaseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseDTO, D extends BaseResponseDTO, E extends BaseEntity> implements BaseService<T, D, E> {

    protected final BaseRepository<E> baseRepository;
    protected final BaseMapper<T, D, E> baseMapper;

    public void detachedAssociations(E e) {

    }

    public void prePersist(E e) {
        e.setUuid(UUID.randomUUID());
        detachedAssociations(e);
    }

    public void postPersist(E e) {

    }

    @Override
    public E save(T t) {
        var entity = baseMapper.toEntity(t);
        prePersist(entity);
        entity = baseRepository.save(entity);
        postPersist(entity);
        return entity;
    }

    @Override
    public E update(UUID uuid, T t) {
        var entity = findByUuid(uuid);
        baseMapper.copyProperties(t, entity);
        return baseRepository.save(entity);
    }

    @Override
    public E findByUuid(UUID uuid) {
        return baseRepository.findByUuid(uuid)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(UUID uuid) {
        var entity = findByUuid(uuid);
        baseRepository.delete(entity);
    }

    @Override
    public Page<D> findAll(Pageable pageable) {
        var page = baseRepository.findAll(pageable);
        return new PageImpl<>(
                page.getContent().stream().map(baseMapper::toDTO).toList(),
                pageable,
                page.getContent().size()
        );
    }

}
