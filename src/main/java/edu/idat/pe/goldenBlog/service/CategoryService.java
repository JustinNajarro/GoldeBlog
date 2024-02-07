package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.Utils.UtilsLibrary;
import edu.idat.pe.goldenBlog.constants.LibraryConstants;
import edu.idat.pe.goldenBlog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import edu.idat.pe.goldenBlog.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Collection<Category> findAllCategories() {
        return (Collection<Category>) categoryRepository.findAll();
    }

    public ResponseEntity<String> saveCategory(Category category) {

        if(!validateCategoryData(category)){
            return UtilsLibrary.getResponseEntity(LibraryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }

        Category existingCategory = categoryRepository.findByNameCategory(category.getNameCategory());
        if(existingCategory != null){
            return UtilsLibrary.getResponseEntity("Esta categoria ya existe",HttpStatus.BAD_REQUEST);
        }

        categoryRepository.save(category);
        return UtilsLibrary.getResponseEntity("La categoria fue registrada con exito", HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryUpdate){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            return UtilsLibrary.getResponseEntity("No existe la categoria con id: "+categoryId,HttpStatus.BAD_REQUEST);
        }

        if(!validateCategoryData(categoryUpdate)){
            return UtilsLibrary.getResponseEntity(LibraryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }

        Category existingCategory = categoryRepository.findByNameCategoryAndIdCategoryNot(categoryUpdate.getNameCategory(), categoryId);
        if(existingCategory != null){
            return UtilsLibrary.getResponseEntity("Esta categoria ya existe",HttpStatus.BAD_REQUEST);
        }

        category.get().setNameCategory(categoryUpdate.getNameCategory());
        return UtilsLibrary.getResponseEntity("La categoria fue actualizada con exito", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            return UtilsLibrary.getResponseEntity("No existe la categoria con id: "+categoryId,HttpStatus.BAD_REQUEST);
        }
        else{
            categoryRepository.deleteById(categoryId);
            return UtilsLibrary.getResponseEntity("La categoria fue eliminada con exito", HttpStatus.OK);
        }
    }

    public static boolean validateCategoryData(Category category) {
        return category.getNameCategory() != null && !category.getNameCategory().isEmpty();
    }
}
