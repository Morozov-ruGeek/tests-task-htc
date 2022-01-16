package ru.amorozov.task.htc.repositories.jdbcimpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;
import ru.amorozov.task.htc.entities.User;
import ru.amorozov.task.htc.repositories.UserRepository;
import ru.amorozov.task.htc.utils.ResourceReader;
import ru.amorozov.task.htc.utils.UserExtractor;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
@Slf4j
@ConditionalOnClass(DataSource.class)
public class JdbcUserRepository implements UserRepository {

    private static final String FIND_USER_BY_ID_SQL_QUERY_PATH = "queries/find-user-by-id.sql";
    private static final String IS_EXIST_ID_SQL_QUERY_PATH = "queries/is-exist-id.sql";

    private final JdbcTemplate jdbcTemplate;
    private final ResourceReader resourceReader;
    private final UserExtractor userExtractor;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate,
                              ResourceReader resourceReader,
                              UserExtractor userExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceReader = resourceReader;
        this.userExtractor = userExtractor;
    }

    @Override
    public Optional<User> findById(Long id) {
        final String findStudentByIdSql = resourceReader.readFileToString(FIND_USER_BY_ID_SQL_QUERY_PATH);
        User user = jdbcTemplate.query(findStudentByIdSql, userExtractor, id).get(0);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isExist(Long id) {
        final String findStudentByIdSql = resourceReader.readFileToString(IS_EXIST_ID_SQL_QUERY_PATH);
        RowCountCallbackHandler handler = new RowCountCallbackHandler();
        jdbcTemplate.query(findStudentByIdSql, handler, id);
        return handler.getRowCount() > 0;
    }
}
