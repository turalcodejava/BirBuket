package com.birbuket.repository;

import com.birbuket.entity.UserEntity;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<@NonNull UserEntity,@NonNull Long> {

    Optional<@NonNull UserEntity> findByUsername(@NonNull String username);

    boolean existsByUsername(String username);
}
