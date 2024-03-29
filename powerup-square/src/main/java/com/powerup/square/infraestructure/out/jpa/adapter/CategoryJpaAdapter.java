package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.spi.ICategoryPersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.powerup.square.infraestructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;
    @Override
    public Category saveCategory(Category category) {
        return categoryMapper.toCategory(categoryRepository.save(categoryMapper.toEntity(category)));
    }
    @Override
    public List<Category> getAllCategory() {
        return null;
    }
    @Override
    public Category getCategory(Long id) {
        return categoryMapper.toCategory(categoryRepository.findById(id).get());
    }
    @Override
    public boolean existByName(String name) {
        return categoryRepository.existsByName(name);
    }
    @Override
    public boolean existById(Long id) {
        return categoryRepository.existsById(id);
    }
}
