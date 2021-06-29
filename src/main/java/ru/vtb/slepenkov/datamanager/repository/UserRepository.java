package ru.vtb.slepenkov.datamanager.repository;

import ru.vtb.slepenkov.datamanager.model.QUser;
import ru.vtb.slepenkov.datamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseRepository<User, Long, QUser> {

}
