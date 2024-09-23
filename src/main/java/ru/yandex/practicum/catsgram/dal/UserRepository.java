package ru.yandex.practicum.catsgram.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.catsgram.dal.mappers.UserRowMapper;
import ru.yandex.practicum.catsgram.dto.UserDto;
import ru.yandex.practicum.catsgram.mapper.UserMapper;
import ru.yandex.practicum.catsgram.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserRowMapper mapper;

    public List<UserDto> findAll() {
        String query = "SELECT * FROM users";
        List<User> users = jdbc.query(query, mapper);
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    public Optional<User> findUserById(long authorId) {
        String query = "SELECT * FROM users WHERE id=" + authorId;
        return Optional.ofNullable(jdbc.query(query, mapper).get(0));
    }

    public User create(User user) {
        String query = "INSERT into users (username, email, password, registration_date) VALUES ( ?, ?, ?, ?)";
        jdbc.update(query, user.getUsername(), user.getEmail(), user.getPassword(), LocalDate.now());
        return user;
    }
}
