SELECT u.ID              AS user_id,
       u.FIRST_NAME      AS user_first_name,
       u.LAST_NAME       AS user_last_name
FROM users u
WHERE u.id = ?