package uz.tulaev.hibernate.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.tulaev.hibernate.model.UserPerson;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserPerson,Long> {

    @Query("select u from UserPerson u where u.email = ?1")
    UserPerson findByEmail(String username);

    @Query("select u from UserPerson u where upper(u.email) = upper(?1)")
    Optional<UserPerson> checkUniqueFields(String email);

    Optional<UserPerson> findByEmailLike(String email);

    @Query("select u from UserPerson u")
    Optional<Collection<UserPerson>> findAllUserDetails();

}
