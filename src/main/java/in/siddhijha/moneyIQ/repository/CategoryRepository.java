package in.siddhijha.moneyIQ.repository;

import in.siddhijha.moneyIQ.entiity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    //select * from tbl_categories where profile_id=?
    List<CategoryEntity>findByProfileId(long profileId);
    Optional<CategoryEntity> findByIdAndProfileId(long id,long profileId);
    List<CategoryEntity>findByTypeAndProfileId(String type,long profileId);
    Boolean existsByNameAndProfileId(String name,long profileId);
}
