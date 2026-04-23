# Docker Configuration for Prediction Cup Application

This document explains how to run the Prediction Cup application using Docker.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)
- At least 4GB of available RAM
- Ports 8080, 8081, and 3306 available

## Quick Start

```bash
# Build and start all services
docker-compose up --build -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes (full cleanup)
docker-compose down -v
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

## Useful Docker Compose Commands

```bash
# Start services
docker-compose up -d                    # Start in background
docker-compose up --build -d            # Rebuild and start

# Status and logs
docker-compose ps                       # Show container status
docker-compose logs -f                  # Follow all logs
docker-compose logs -f app              # Follow specific service logs

# Restart
docker-compose restart                  # Restart all services
docker-compose restart app              # Restart specific service

# Rebuild specific service
docker-compose up -d --build app        # Rebuild only app

# Shell access
docker exec -it prediction-app sh       # Enter app container
docker exec -it prediction-mysql bash   # Enter MySQL container

# Cleanup
docker-compose down                     # Stop and remove containers
docker-compose down -v                  # Stop, remove containers and volumes
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
docker-compose up --build -d
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
docker-compose up -d --build app
```

### 2. Database Changes

If you need to reset the database:

```bash
# Stop services and remove volumes
docker-compose down -v

# Start services (will recreate database)
docker-compose up --build -d
```

### 3. Viewing Logs

```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f app
docker-compose logs -f mysql
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
   docker-compose logs mysql

   # Restart services
   docker-compose restart
   ```

3. **Application Won't Start**
   ```bash
   # Check application logs
   docker-compose logs app

   # Rebuild the application
   docker-compose up -d --build app
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

To completely remove all containers and volumes:

```bash
# Remove containers and volumes
docker-compose down -v

# Additionally remove images (optional)
docker-compose down -v --rmi all
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
├── docker/
│   └── mysql/
│       └── init/             # Database initialization scripts
│           ├── 00-create-tables.sql
│           ├── 01-init.sql
│           └── 02-sample-data.sql
└── src/
    └── main/
        └── resources/
            └── application.properties
```

## Support

If you encounter issues:

1. Check the logs: `docker-compose logs -f`
2. Verify Docker is running: `docker info`
3. Check port availability: `lsof -i :8080`
4. Restart services: `docker-compose restart`
