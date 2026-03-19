package com.birbuket.repository;

import com.birbuket.entity.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<@NonNull UserEntity,@NonNull Long> {

    Optional<@NonNull UserEntity> findByUsername(@NonNull String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String attr0);

    boolean existsByPhoneNumber(@NotBlank(message = "Phone number boş ola bilməz") @Pattern(regexp = "^\\+994[0-9]{9}$", message = "Telefon nömrəsi +994XXXXXXXXX formatında olmalıdır") String phoneNumber);
}
