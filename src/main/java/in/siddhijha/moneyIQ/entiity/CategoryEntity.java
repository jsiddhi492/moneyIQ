package in.siddhijha.moneyIQ.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String type;
    private String icon;
    //When you fetch a Category, Spring DOES NOT load the Profile immediately. It loads the Profile ONLY when you first use it.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",nullable = false)
    private ProfileEntity profile;



}
