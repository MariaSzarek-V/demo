#!/bin/bash
# This script ensures proper UTF-8 encoding for MySQL
# It runs first (00-) before any SQL scripts

echo "Configuring MySQL for UTF-8 (utf8mb4) encoding..."

# Create a MySQL configuration to ensure UTF-8 is used
mysql --protocol=socket -uroot -p"${MYSQL_ROOT_PASSWORD}" --default-character-set=utf8mb4 <<-EOSQL
    SET NAMES utf8mb4;
    SET CHARACTER SET utf8mb4;
    SET character_set_client = utf8mb4;
    SET character_set_connection = utf8mb4;
    SET character_set_database = utf8mb4;
    SET character_set_results = utf8mb4;
    SET character_set_server = utf8mb4;
    SET collation_connection = utf8mb4_unicode_ci;
    SET collation_database = utf8mb4_unicode_ci;
    SET collation_server = utf8mb4_unicode_ci;

    ALTER DATABASE ${MYSQL_DATABASE} CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EOSQL

echo "UTF-8 encoding configured successfully for database ${MYSQL_DATABASE}"
