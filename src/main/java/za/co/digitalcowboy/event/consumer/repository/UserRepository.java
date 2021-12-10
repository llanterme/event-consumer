package za.co.digitalcowboy.event.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.digitalcowboy.event.consumer.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

}
