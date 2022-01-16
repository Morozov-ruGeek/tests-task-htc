package ru.amorozov.task.htc.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.amorozov.task.htc.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserExtractor implements ResultSetExtractor<List<User>> {

    private static final String USER_ID_COLUMN = "user_id";
    private static final String USER_FIRST_NAME_COLUMN = "user_first_name";
    private static final String USER_LAST_NAME_COLUMN = "user_last_name";

    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, User> userMap = new HashMap<>();
        while (resultSet.next()) {
            long userId = resultSet.getLong(USER_ID_COLUMN);
            User user = userMap.get(userId);
            if (user == null) {
                user = new User();
                user.setFirstName(resultSet.getString(USER_FIRST_NAME_COLUMN));
                user.setLastName(resultSet.getString(USER_LAST_NAME_COLUMN));
            }
            userMap.put(userId, user);
        }
        return new ArrayList<>(userMap.values());
    }
}
