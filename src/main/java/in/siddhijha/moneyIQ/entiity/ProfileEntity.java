package in.siddhijha.moneyIQ.entiity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_profiles")
@Data  //generates getters and setters
@AllArgsConstructor //creates constructor with args
@NoArgsConstructor //for creating default constructors so that jpa first instantiate the object and then sets the value
@Builder
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //used for auto-increment the id every time
   private Long id;
    @Column(unique = true)
   private String fullName;
   private String email;
   private String password;
   private String profileImageUrl;
   @Column(updatable = false)
   @CreationTimestamp
   private LocalDateTime createdAt;
   @UpdateTimestamp
   private LocalDateTime updatedAt;
   private Boolean isActive;
   private String activationToken;

    //we are not going to store null values into the database that's why we are checking first and then store
   @PrePersist
    public void prePersist(){
       if(this.isActive==null){
           isActive=false;
       }
   }

}
