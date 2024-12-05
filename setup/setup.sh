#!/bin/bash

MYSQL_CONTAINER_NAME="vs"
MYSQL_ROOT_PASSWORD="pass"
MYSQL_DATABASE="VS"
SQL_FILES=("create-table.sql" "insert-user.sql" "insert-candidate.sql" "insert-person.sql" "insert-failed-attempt.sql")

docker run --name $MYSQL_CONTAINER_NAME \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -e MYSQL_CHARSET=utf8mb4 \
  -e MYSQL_COLLATION=utf8mb4_unicode_ci \
  -p 3306:3306 -d mysql:latest

sleep 30

for sql_file in "${SQL_FILES[@]}"; do
    docker exec -i $MYSQL_CONTAINER_NAME mysql -u root -p$MYSQL_ROOT_PASSWORD $MYSQL_DATABASE < "$sql_file"
done
