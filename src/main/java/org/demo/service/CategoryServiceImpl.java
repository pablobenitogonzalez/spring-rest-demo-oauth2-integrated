package org.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.demo.domain.Category;
import org.demo.exception.ResourceNotFoundException;
import org.demo.repository.CategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(messageService.getMessage("message.category.id.null"));
        }
        Category category = categoryRepository.findOne(id);
        if(category == null) {
            throw new ResourceNotFoundException(messageService.getMessage("message.category.not.found", new Object[]{id}));
        }
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllOrderByName();
    }

    public Category createCategory(@NotNull
                                   @Valid
                                   Category category) {
        if(categoryRepository.findByName(category.getName()) != null) {
            throw new DuplicateKeyException(messageService.getMessage("message.category.duplicate.key", new Object[]{category.getName()}));
        }
        return categoryRepository.save(category);
    }

    public void updateCategory(@NotNull
                               @Valid
                               Category category) {
        Category oldCategory = this.getCategory(category.getId());
        if(!oldCategory.getName().equals(category.getName()) && categoryRepository.findByName(category.getName()) != null) {
            throw new DuplicateKeyException(messageService.getMessage("message.category.duplicate.key", new Object[]{category.getName()}));
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = this.getCategory(id);
        if(category.getSubcategories().size() > 0) {
            throw new DataIntegrityViolationException(messageService.getMessage("message.category.data.integrity", new Object[]{id}));
        }
        categoryRepository.delete(category);
    }
}
