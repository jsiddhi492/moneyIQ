package in.siddhijha.moneyIQ.repository;

import in.siddhijha.moneyIQ.entiity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Long> {
    //optional for handling null value and find by email helps to automatically write sql query
    //like select * from tbl_profile where email='email';
    Optional<ProfileEntity> findByEmail(String email);
    //like select * from tbl_profile where activationToken='activationToken';
    Optional<ProfileEntity> findByActivationToken(String activationToken);
}
