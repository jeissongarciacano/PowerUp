package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    List<Category> getAllCategory();
    Category getCategory(Long id);
    boolean existByName(String name);
    boolean existById(Long id);
}
