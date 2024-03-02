package io.elice.pokeranger.user.repository;
import io.elice.pokeranger.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, Long> {


}

