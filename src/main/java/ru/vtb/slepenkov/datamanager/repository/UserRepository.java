package ru.vtb.slepenkov.datamanager.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import ru.vtb.slepenkov.datamanager.model.QUser;
import ru.vtb.slepenkov.datamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends BaseRepository<User, Long, QUser> {
    @Override
    @EntityGraph(attributePaths = {"vacationsList"})
    Optional<User> findOne(Predicate predicate);

}
