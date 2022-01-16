SELECT a.name AS author_name,
       (SELECT COUNT(*) FROM BOOKS b WHERE b.author_id = a.id) AS count
FROM authors a
         LEFT JOIN books b
                   ON a.id
GROUP BY a.name;