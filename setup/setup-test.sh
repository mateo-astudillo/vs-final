#!/bin/bash

MYSQL_CONTAINER_NAME="vs-test"
MYSQL_ROOT_PASSWORD="test"
MYSQL_DATABASE="VST"
SQL_FILES=("create-table.sql" "insert-user.sql" "insert-user-test.sql" "insert-candidate.sql" "insert-person.sql" "insert-failed-attempt.sql" "insert-test-votes.sql")

docker run --name $MYSQL_CONTAINER_NAME \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -e MYSQL_CHARSET=utf8mb4 \
  -e MYSQL_COLLATION=utf8mb4_unicode_ci \
  -p 3307:3306 -d mysql:latest

sleep 22

for sql_file in "${SQL_FILES[@]}"; do
    docker exec -i $MYSQL_CONTAINER_NAME mysql \
      -u root \
      -p$MYSQL_ROOT_PASSWORD \
      $MYSQL_DATABASE < "$sql_file"
done
