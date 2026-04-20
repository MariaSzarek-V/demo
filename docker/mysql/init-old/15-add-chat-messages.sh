#!/bin/bash
# Wrapper script to execute 15-add-chat-messages.sql with proper UTF-8 encoding

echo "Executing 15-add-chat-messages.sql with UTF-8 encoding..."

# Execute the SQL file with explicit UTF-8 encoding
mysql --protocol=socket -uroot -p"${MYSQL_ROOT_PASSWORD}" --default-character-set=utf8mb4 "${MYSQL_DATABASE}" < /docker-entrypoint-initdb.d/15-add-chat-messages.sql.data

echo "15-add-chat-messages.sql executed successfully"
