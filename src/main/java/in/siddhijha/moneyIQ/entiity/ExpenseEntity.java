package in.siddhijha.moneyIQ.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "tbl_expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String name;
   private String icon;
   private LocalDate date;
   private BigDecimal amount;
   @Column(updatable = false)
   @CreationTimestamp
   private LocalDateTime createdAt;
   @UpdateTimestamp
   private LocalDateTime updateAt;
   //Many expenses belong to one category.
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id",nullable = false)
   private CategoryEntity category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",nullable = false)
    private ProfileEntity profile;

    //before  saving this new record, run this method first.
    @PrePersist
    private void prePersist(){
        if(this.date==null){
            this.date=LocalDate.now();
        }
    }

}
