package br.com.genius_finance.service;

import br.com.genius_finance.model.entity.BaseEntity;
import br.com.genius_finance.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class BaseServiceImpl<E extends BaseEntity> implements BaseService<E> {

    protected final BaseRepository<E> baseRepository;

    @Override
    public E save(E e) {
        return this.baseRepository.save(e);
    }

    @Override
    public E findByUuid(UUID uuid) {
        return this.baseRepository.findByUUID(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return this.baseRepository.findAll(pageable);
    }

    @Override
    public E update(UUID uuid, E e) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        var entity = this.findByUuid(uuid);
        this.baseRepository.delete(entity);
    }
}
