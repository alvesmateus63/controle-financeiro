package br.com.alvesmateus.controleFinanceiro.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alvesmateus.controleFinanceiro.category.dto.CategoryRequest;
import br.com.alvesmateus.controleFinanceiro.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllBankAccounts() {
        List<CategoryResponse> response = categoryService.getAllCategories();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse response = categoryService.getCategory(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
        @PathVariable Long id, 
        @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }

}
