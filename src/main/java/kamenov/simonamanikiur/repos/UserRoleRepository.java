package kamenov.simonamanikiur.repos;

import kamenov.simonamanikiur.entity.UserRoleEntity;
import kamenov.simonamanikiur.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);
}
