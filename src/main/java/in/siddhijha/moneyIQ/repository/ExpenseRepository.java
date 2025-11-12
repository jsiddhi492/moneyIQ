package in.siddhijha.moneyIQ.repository;

import in.siddhijha.moneyIQ.entiity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
    List<ExpenseEntity> findByProfileIdOrderByDateDesc(long profileId);
    List<ExpenseEntity>findTop5ByProfileIdOrderByDateDesc(long profileId);
    @Query("SELECT SUM(E.amount) FROM ExpenseEntity e WHERE e.profile.id= :profileId")
    BigDecimal   findTotalExpenseByProfileId(@Param("profileId") Long profileId);


}
