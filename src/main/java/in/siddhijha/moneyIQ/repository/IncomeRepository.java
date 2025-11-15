package in.siddhijha.moneyIQ.repository;

import in.siddhijha.moneyIQ.entiity.ExpenseEntity;
import in.siddhijha.moneyIQ.entiity.IncomeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeEntity,Long> {
    List<IncomeEntity> findByProfileIdOrderByDateDesc(long profileId);
    List<IncomeEntity>findTop5ByProfileIdOrderByDateDesc(long profileId);
    @Query("SELECT SUM(i.amount) FROM IncomeEntity i WHERE i.profile.id= :profileId")
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") Long profileId);
    //select* from tbl_incomes where profile_id=?1 and date between start and end date and name like %keyword%
    List<IncomeEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );
    List<IncomeEntity> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);

}
