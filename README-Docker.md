# Docker Configuration for Prediction Cup Application

This document explains how to run the Prediction Cup application using Docker.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)
- At least 4GB of available RAM
- Ports 8080, 8081, and 3306 available

## Quick Start

### 1. Using the Management Script (Recommended)

```bash
# Make the script executable (if not already done)
chmod +x docker-run.sh

# Start the application
./docker-run.sh start

# Check status
./docker-run.sh status

# View logs
./docker-run.sh logs

# Stop the application
./docker-run.sh stop
```

### 2. Using Docker Compose Directly

```bash
# Build and start all services
docker-compose up --build -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f
```

## Services

The Docker setup includes the following services:

### 1. **MySQL Database** (`mysql`)
- **Port**: 3306
- **Database**: `betsdb`
- **Username**: `root`
- **Password**: `root`
- **Auto-initialization**: Pliki SQL w `docker/mysql/init/` są automatycznie wykonywane przy pierwszym uruchomieniu

### 2. **Spring Boot Application** (`app`)
- **Port**: 8080
- **Main URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health

### 3. **phpMyAdmin** (`phpmyadmin`)
- **Port**: 8081
- **URL**: http://localhost:8081
- **Username**: `prediction_user`
- **Password**: `prediction_password`

## Default Users

The application comes with pre-configured users:

### Admin User
- **Username**: `admin`
- **Password**: `admin123`
- **Role**: ADMIN

### Test User  
- **Username**: `testuser`
- **Password**: `password123`
- **Role**: USER

## Management Script Commands

The `docker-run.sh` script provides the following commands:

```bash
./docker-run.sh start      # Build and start all services
./docker-run.sh stop       # Stop all services
./docker-run.sh restart    # Restart all services
./docker-run.sh status     # Show container status
./docker-run.sh logs       # Show logs for all services
./docker-run.sh logs app   # Show logs for specific service
./docker-run.sh rebuild    # Rebuild application container
./docker-run.sh shell      # Open shell in app container
./docker-run.sh shell mysql # Open shell in MySQL container
./docker-run.sh clean      # Remove all containers and volumes
./docker-run.sh help       # Show help
```

## Environment Variables

The application supports the following environment variables (configured in docker-compose.yml):

### Database Configuration
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_DATASOURCE_DRIVER_CLASS_NAME`

### JPA/Hibernate Configuration
- `SPRING_JPA_HIBERNATE_DDL_AUTO`
- `SPRING_JPA_SHOW_SQL`
- `SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT`

### Application Configuration
- `SERVER_PORT`
- `LOGGING_LEVEL_ROOT`
- `SPRING_SECURITY_USER_NAME`
- `SPRING_SECURITY_USER_PASSWORD`

## Automatyczna Inicjalizacja Bazy Danych

Aplikacja automatycznie inicjalizuje bazę danych przy pierwszym uruchomieniu kontenera MySQL. Skrypty SQL są wykonywane w kolejności alfabetycznej z katalogu `docker/mysql/init/`:

### Pliki Inicjalizacyjne

1. **00-create-tables.sql**
   - Tworzy schemat bazy danych
   - Definiuje wszystkie tabele (user, game, prediction, comment, user_points, ranking, etc.)
   - Ustawia klucze obce i indeksy

2. **01-init.sql**
   - Tworzy bazę danych `betsdb`
   - Dodaje domyślnych użytkowników (admin, testuser)
   - Tworzy przykładowe gry i predykcje

3. **02-sample-data.sql**
   - Dodaje więcej przykładowych danych
   - Tworzy dodatkowych użytkowników testowych
   - Wypełnia tabelę ranking i user_points

### Ważne Informacje

- **Pierwsze uruchomienie**: Skrypty SQL wykonują się TYLKO przy pierwszym utworzeniu kontenera
- **Ponowna inicjalizacja**: Aby ponownie zainicjalizować bazę danych, usuń volume: `docker-compose down -v`
- **Hibernate DDL**: Ustawione na `none` - schemat jest zarządzany przez skrypty SQL, nie przez Hibernate
- **Hasła**: Wszystkie testowe hasła to `password123` (zahaszowane BCrypt)

### Przykład: Reset Bazy Danych

```bash
# Zatrzymaj i usuń wszystkie kontenery wraz z volumes
docker-compose down -v

# Uruchom ponownie (baza zostanie utworzona od nowa)
./docker-run.sh start
```

## Volumes

- `mysql_data`: Persistent storage for MySQL database
- `./docker/mysql/init`: Database initialization scripts (montowany tylko do odczytu)

## Network

All services run on the `prediction-network` bridge network, allowing them to communicate with each other using service names.

## Development Workflow

### 1. Making Code Changes

After making changes to your Java code:

```bash
# Rebuild and restart the application
./docker-run.sh rebuild
```

### 2. Database Changes

If you need to reset the database:

```bash
# Stop services and remove volumes
docker-compose down -v

# Start services (will recreate database)
./docker-run.sh start
```

### 3. Viewing Logs

```bash
# All services
./docker-run.sh logs

# Specific service
./docker-run.sh logs app
./docker-run.sh logs mysql
```

## Troubleshooting

### Common Issues

1. **Port Already in Use**
   ```bash
   # Check what's using the port
   lsof -i :8080
   
   # Kill the process or change the port in docker-compose.yml
   ```

2. **Database Connection Issues**
   ```bash
   # Check if MySQL is ready
   ./docker-run.sh logs mysql
   
   # Restart services
   ./docker-run.sh restart
   ```

3. **Application Won't Start**
   ```bash
   # Check application logs
   ./docker-run.sh logs app
   
   # Rebuild the application
   ./docker-run.sh rebuild
   ```

### Health Checks

The setup includes health checks for both MySQL and the application:

```bash
# Check container health
docker-compose ps

# Manual health check
curl http://localhost:8080/actuator/health
```

### Cleanup

To completely remove all containers, volumes, and images:

```bash
./docker-run.sh clean
```

## Production Considerations

For production deployment, consider:

1. **Security**:
   - Change default passwords
   - Use secrets management
   - Enable HTTPS
   - Restrict network access

2. **Performance**:
   - Adjust JVM memory settings
   - Configure connection pooling
   - Set up monitoring

3. **Persistence**:
   - Use external database
   - Configure backup strategies
   - Set up log aggregation

4. **Scalability**:
   - Use container orchestration (Kubernetes)
   - Implement load balancing
   - Configure auto-scaling

## File Structure

```
.
├── Dockerfile                 # Application container definition
├── docker-compose.yml         # Multi-service configuration
├── .dockerignore             # Files to exclude from build
├── docker-run.sh             # Management script
├── docker/
│   └── mysql/
│       └── init/
│           └── 01-init.sql   # Database initialization
└── src/
    └── main/
        └── resources/
            └── application.properties # Updated with Docker support
```

## Support

If you encounter issues:

1. Check the logs: `./docker-run.sh logs`
2. Verify Docker is running: `docker info`
3. Check port availability: `netstat -tulpn | grep :8080`
4. Restart services: `./docker-run.sh restart`
