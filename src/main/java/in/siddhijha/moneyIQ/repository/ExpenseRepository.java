package in.siddhijha.moneyIQ.repository;

import in.siddhijha.moneyIQ.entiity.ExpenseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
    List<ExpenseEntity> findByProfileIdOrderByDateDesc(long profileId);
    List<ExpenseEntity>findTop5ByProfileIdOrderByDateDesc(long profileId);
    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e WHERE e.profile.id= :profileId")
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") Long profileId);
    //select* from tbl_expenses where profile_id=?1 and date between start and end date and name like %keyword%
    List<ExpenseEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
        Long profileId,
        LocalDate startDate,
        LocalDate endDate,
        String keyword,
        Sort sort
    );
    List<ExpenseEntity> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);
    List<ExpenseEntity> findByProfileIdAndDate(Long profileId,LocalDate date);
}
