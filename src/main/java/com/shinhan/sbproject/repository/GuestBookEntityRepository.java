package com.shinhan.sbproject.repository;

import com.shinhan.sbproject.entity.GuestBookEntity;
import org.springframework.data.repository.CrudRepository;

public interface GuestBookEntityRepository extends CrudRepository<GuestBookEntity, Long> {
}
