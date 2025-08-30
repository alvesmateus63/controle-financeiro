package br.com.alvesmateus.controleFinanceiro.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.alvesmateus.controleFinanceiro.category.dto.CategoryRequest;
import br.com.alvesmateus.controleFinanceiro.category.dto.CategoryResponse;
import br.com.alvesmateus.controleFinanceiro.user.User;
import br.com.alvesmateus.controleFinanceiro.user.UserService;
import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public List<CategoryResponse> getAllCategories() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Category> Categorys = categoryRepository.findByUser(loggedUser);

        return Categorys.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public CategoryResponse getCategory(Long id) {
        Category Category = categoryRepository.findById(id).orElseThrow();

        return toResponseDTO(Category);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category newCategory = new Category();
        newCategory.setUser(loggedUser);
        newCategory.setName(request.name());

        Category savedCategory = categoryRepository.save(newCategory);

        return toResponseDTO(savedCategory);
    }

    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var existingCategory = categoryRepository
            .findByIdAndUserId(id, loggedUser.getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setName(request.name());

        return toResponseDTO(existingCategory);
    }

    @Transactional
    public void deleteById(Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var categoryToDelete = categoryRepository
            .findByIdAndUserId(id, loggedUser.getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));


        categoryRepository.delete(categoryToDelete);
    }

    private CategoryResponse toResponseDTO(Category category) {

        UserResponse userResponse = userService.toResponseDTO(category.getUser());

        return new CategoryResponse(
            category.getId(),
            category.getName(),
            userResponse
        );
    }

}
