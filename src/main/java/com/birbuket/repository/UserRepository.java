package com.birbuket.repository;

import com.birbuket.entity.UserEntity;
import lombok.NonNull;
import lombok.experimental.NonFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<@NonNull UserEntity, @NonNull Long> {
}
