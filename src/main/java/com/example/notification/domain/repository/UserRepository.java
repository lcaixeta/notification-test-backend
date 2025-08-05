package com.example.notification.domain.repository;

import com.example.notification.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long Id);

    @Query(value = "" +
            "SELECT us.* FROM public.user us " +
            "INNER JOIN public.subscription sp ON us.id = sp.user_id " +
            "WHERE sp.category_id = :categoryId " +
            "", nativeQuery = true)
    List<User> findAllByCategory(@Param("categoryId") long categoryId);

    @Query(value = "" +
            "SELECT us.* FROM public.user us " +
            "INNER JOIN public.channels ch ON us.id = ch.user_id " +
            "WHERE ch.notification_type_id = :notificationTypeId " +
            "", nativeQuery = true)
    List<User> findAllByNotificationType(@Param("notificationTypeId") long notificationTypeId);
}
