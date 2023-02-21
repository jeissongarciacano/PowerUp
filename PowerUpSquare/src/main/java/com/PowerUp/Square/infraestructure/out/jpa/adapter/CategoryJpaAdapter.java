package com.PowerUp.Square.infraestructure.out.jpa.adapter;

import com.PowerUp.Square.infraestructure.out.jpa.entity.CategoryEntity;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.PowerUp.Square.infraestructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryJpaAdapter {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    public CategoryEntity saveCategoryEntity(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }
    public List<CategoryEntity> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryEntity editCategory(CategoryEntity categoryEntity){
        if(categoryRepository.existsById(categoryEntity.getId())){
            return categoryRepository.save(categoryEntity);
        }
        return null;
    }

    public boolean existByID(Long id) {
        return categoryRepository.existsById(id);
    }
}
