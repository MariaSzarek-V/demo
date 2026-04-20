#!/bin/bash

# Script to completely rebuild the database with fresh data
# Use this when you change SQL initialization scripts

echo "🛑 Stopping all containers..."
docker-compose down -v

echo "🧹 Cleaning up Docker volumes..."
docker volume rm demo_mysql_data 2>/dev/null || echo "Volume already removed"

echo "🧹 Pruning unused volumes..."
docker volume prune -f

echo "🚀 Starting containers with fresh database..."
docker-compose up -d

echo "⏳ Waiting for MySQL to initialize (this takes ~20 seconds)..."
sleep 20

echo "✅ Checking database status..."
docker exec -i prediction-mysql mysql -uroot -proot betsdb -e "SELECT COUNT(*) as users FROM user; SELECT COUNT(*) as predictions FROM prediction; SELECT COUNT(*) as points FROM user_points;" 2>/dev/null

echo "📊 Top 5 users by points:"
docker exec -i prediction-mysql mysql -uroot -proot betsdb --batch -e "SELECT u.username, SUM(up.points) as total_points FROM user u JOIN user_points up ON u.id = up.user_id WHERE up.league_id = 1 GROUP BY u.id, u.username ORDER BY total_points DESC LIMIT 5;" 2>/dev/null

echo ""
echo "✅ Database rebuild complete!"
echo "🌐 Access the application at: http://localhost:3000"
