package com.shinhan.sbproject.repository2;

import com.shinhan.sbproject.entity2.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
