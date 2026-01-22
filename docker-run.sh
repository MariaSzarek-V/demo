#!/bin/bash

# Docker run script for Prediction Cup application
# This script provides easy commands to manage the Docker environment

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to check if Docker is running
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        print_error "Docker is not running. Please start Docker and try again."
        exit 1
    fi
}

# Function to build and start the application
start() {
    print_status "Starting Prediction Cup application..."
    check_docker
    
    print_status "Building and starting containers..."
    docker-compose up --build -d
    
    print_status "Waiting for services to be ready..."
    sleep 10
    
    # Check if services are running
    if docker-compose ps | grep -q "Up"; then
        print_success "Application started successfully!"
        print_status "Services available at:"
        echo "  - Application: http://localhost:8080"
        echo "  - Swagger UI: http://localhost:8080/swagger-ui.html"
        echo "  - phpMyAdmin: http://localhost:8081"
        echo ""
        echo "Default credentials:"
        echo "  - Admin user: admin / admin123"
        echo "  - Test user: testuser / password123"
        echo "  - Database: prediction_user / prediction_password"
    else
        print_error "Failed to start some services. Check logs with: ./docker-run.sh logs"
    fi
}

# Function to stop the application
stop() {
    print_status "Stopping Prediction Cup application..."
    check_docker
    docker-compose down
    print_success "Application stopped successfully!"
}

# Function to restart the application
restart() {
    print_status "Restarting Prediction Cup application..."
    stop
    start
}

# Function to show logs
logs() {
    check_docker
    if [ -n "$2" ]; then
        print_status "Showing logs for service: $2"
        docker-compose logs -f "$2"
    else
        print_status "Showing logs for all services..."
        docker-compose logs -f
    fi
}

# Function to show status
status() {
    check_docker
    print_status "Docker containers status:"
    docker-compose ps
}

# Function to clean up everything
clean() {
    print_warning "This will remove all containers, volumes, and images related to this project."
    read -p "Are you sure? (y/N): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        check_docker
        print_status "Stopping and removing containers..."
        docker-compose down -v --remove-orphans
        
        print_status "Removing images..."
        docker rmi $(docker images | grep prediction | awk '{print $3}') 2>/dev/null || true
        
        print_success "Cleanup completed!"
    else
        print_status "Cleanup cancelled."
    fi
}

# Function to rebuild application only
rebuild() {
    print_status "Rebuilding application container..."
    check_docker
    docker-compose build --no-cache app
    docker-compose up -d app
    print_success "Application rebuilt and restarted!"
}

# Function to enter container shell
shell() {
    check_docker
    if [ -n "$2" ]; then
        service="$2"
    else
        service="app"
    fi
    
    print_status "Opening shell in $service container..."
    docker-compose exec "$service" /bin/bash
}

# Function to show help
help() {
    echo "Prediction Cup Docker Management Script"
    echo ""
    echo "Usage: $0 [COMMAND]"
    echo ""
    echo "Commands:"
    echo "  start     Build and start all services"
    echo "  stop      Stop all services"
    echo "  restart   Restart all services"
    echo "  status    Show status of all containers"
    echo "  logs      Show logs (optionally specify service name)"
    echo "  rebuild   Rebuild and restart application container"
    echo "  shell     Open shell in container (default: app)"
    echo "  clean     Remove all containers, volumes, and images"
    echo "  help      Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 start"
    echo "  $0 logs app"
    echo "  $0 shell mysql"
    echo "  $0 clean"
}

# Main script logic
case "${1:-help}" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    logs)
        logs "$@"
        ;;
    rebuild)
        rebuild
        ;;
    shell)
        shell "$@"
        ;;
    clean)
        clean
        ;;
    help|--help|-h)
        help
        ;;
    *)
        print_error "Unknown command: $1"
        help
        exit 1
        ;;
esac
