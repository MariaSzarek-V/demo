#!/bin/bash
# Wrapper script to execute 07-chat-messages.sql with proper UTF-8 encoding

echo "Executing 07-chat-messages.sql with UTF-8 encoding..."

# Execute the SQL file with explicit UTF-8 encoding
mysql --protocol=socket -uroot -p"${MYSQL_ROOT_PASSWORD}" --default-character-set=utf8mb4 "${MYSQL_DATABASE}" < /docker-entrypoint-initdb.d/07-chat-messages.sql.data

echo "07-chat-messages.sql executed successfully"
