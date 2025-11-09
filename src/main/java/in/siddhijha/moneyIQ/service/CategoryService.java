package in.siddhijha.moneyIQ.service;

import in.siddhijha.moneyIQ.dto.CategoryDTO;
import in.siddhijha.moneyIQ.entiity.CategoryEntity;
import in.siddhijha.moneyIQ.entiity.ProfileEntity;
import in.siddhijha.moneyIQ.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    //save category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDTO.getName(),profile.getId())){
               throw new RuntimeException("Category with this name already exists");
        }
        CategoryEntity newCategory=toEntity(categoryDTO,profile);
        newCategory=categoryRepository.save(newCategory);
        return toDTO(newCategory);
    }
    public List<CategoryDTO> getCategoriesForCurrentUser(){
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories=categoryRepository.findByProfileId(profile.getId());
        return categories.stream().map(this::toDTO).toList();
    }
    // get category by type
    public List<CategoryDTO>getCategoriesByTypeForCurrentUser(String type){
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> entities=categoryRepository.findByTypeAndProfileId(type,profile.getId());
        return entities.stream().map(this::toDTO).toList();
    }
    //update category
     public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId){
         ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity existingCategory= categoryRepository.findByIdAndProfileId(categoryId,profile.getId())
                 .orElseThrow(()->new RuntimeException(("category not found")));
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setIcon(categoryDTO.getIcon());
        categoryRepository.save(existingCategory);
        return toDTO(existingCategory);
    }
    //helper methods
    private CategoryEntity toEntity(CategoryDTO categoryDTO, ProfileEntity profile){
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .profile(profile)
                .type(categoryDTO.getType())
                .build();
    }
    private CategoryDTO toDTO(CategoryEntity categoryEntity){
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .profileId(categoryEntity.getProfile()!=null ? categoryEntity.getProfile().getId() : null)
                .name(categoryEntity.getName())
                .icon(categoryEntity.getIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .type(categoryEntity.getType())
                .build();

    }

}
