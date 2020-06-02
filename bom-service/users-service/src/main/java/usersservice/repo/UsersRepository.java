package usersservice.repo;

import org.springframework.data.repository.CrudRepository;
import usersservice.model.UserEntity;

import java.util.List;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String username);

    List<UserEntity> findAllByDeleted(boolean b);
}
