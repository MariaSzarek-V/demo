# 🏆 PredictionCup

PredictionCup is a web application for predicting match results, featuring a scoring system and player rankings.
Users can predict game outcomes, earn points, and compete in the ranking system.
Administrators have the ability to manage games.

---

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Running the Application with Docker](#-running-the-application-with-docker)
- [Access the Application](#access-the-application)
- [Managing Containers](#managing-containers)
- [Application Structure](#application-structure)
- [API Endpoints](#api-endpoints)
- [Roadmap](#roadmap)

---

## Features

### User Features

- User registration and login (username-based authentication)
- Change password functionality
- Browse available games (SCHEDULED and FINISHED)
- Submit or update predictions before game start
- Earn points after game completion
- View ranking with current position and position changes compared to previous matches
- View other players' predictions and points after game completion
- Comment section for public discussions

![img.png](img.png)
![img_1.png](img_1.png)

---

### Admin Features

- Add, update, delete games
- Admin-only game view with ADMIN_VIEW status
- Change game status: When status changes to FINISHED, points are automatically calculated
- Restrict deletion of games to those with ADMIN_VIEW status

![img_11.png](docs/img_11.png)

---

## Technology Stack

- **Backend**:
  - Spring Boot
  - Spring Data JPA
  - Spring Security
  - Spring Validation
  - Lombok
  - MySQL
  - OpenAPI (Swagger UI)

- **Frontend**:
  - Thymeleaf
  - Bootstrap 5
  - thymeleaf-extras-springsecurity

- **Infrastructure**:
  - Docker
  - Docker Compose
  - MySQL 8.0
  - phpMyAdmin

---

## 🚀 Running the Application with Docker

### Prerequisites

- **Docker Desktop** (version 20.10 or newer)
- **Docker Compose** (version 2.0 or newer)
- **Frontend repository** cloned next to backend: `/workspace/coderslab/demo_front`

### Repository Structure

```
workspace/coderslab/
├── demo/           (backend - this repository)
└── demo_front/     (frontend - React application)
```

### Check if Docker is installed

```bash
docker --version
docker-compose --version
```

If Docker is not installed, download it from [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

---

### Step 1: Clone the repositories

```bash
# Clone backend
git clone <backend-repo-url> /workspace/coderslab/demo
cd /workspace/coderslab/demo

# Clone frontend (must be in the same parent directory)
cd ..
git clone <frontend-repo-url> demo_front
cd demo
```

---

### Step 2: Start the application

The application uses Docker Compose which automatically:
- Builds the Spring Boot backend application
- Builds the React frontend application
- Creates MySQL database
- Initializes the database with sample data
- Starts phpMyAdmin for database management

**Start all containers:**

```bash
docker-compose up -d
```

This single command starts **all 4 containers**:
1. MySQL database (`prediction-mysql`)
2. Spring Boot backend (`prediction-app`)
3. React frontend (`prediction-frontend`)
4. phpMyAdmin (`prediction-phpmyadmin`)

---

### Step 3: Wait for the application to start

The application needs approximately **60-90 seconds** to fully start. You can monitor the logs:

```bash
# Check container status
docker-compose ps

# View all logs
docker-compose logs -f

# View specific container logs
docker logs prediction-app -f        # Backend
docker logs prediction-frontend -f   # Frontend
docker logs prediction-mysql -f      # Database
```

The application is ready when you see:
```
Started DemoApplication in X.XXX seconds (backend)
```

---

## Access the Application

After starting the application, you can access it at the following addresses:

### 🌐 Main Application (Frontend)
- **React App**: [http://localhost:3000](http://localhost:3000)
- **Login credentials**:
  - Username: `ania` / Password: `123qweasd`
  - Username: `admin` / Password: `Password1`

### 🔧 Backend API
- **API Base URL**: [http://localhost:8080/api](http://localhost:8080/api)
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 🗄️ Database Management
- **phpMyAdmin**: [http://localhost:8081](http://localhost:8081)
  - Server: `mysql`
  - Username: `root`
  - Password: `root`
  - Database: `betsdb`

---

## Managing Containers

### View running containers

```bash
docker-compose ps
```

### Stop all containers

```bash
docker-compose stop
```

Containers are stopped but not removed. Data is preserved.

### Start stopped containers

```bash
docker-compose start
```

### Restart all containers

```bash
docker-compose restart
```

### Restart specific container

```bash
docker-compose restart frontend    # Frontend only
docker-compose restart app         # Backend only
docker-compose restart mysql       # Database only
```

### Stop and remove containers

```bash
docker-compose down
```

Removes containers but keeps volumes (database data is preserved).

### Stop and remove containers with data (⚠️ DELETES DATABASE!)

```bash
docker-compose down -v
```

⚠️ **WARNING**: This command will delete all data from the database!

---

## Rebuilding After Code Changes

### Rebuild frontend after code changes

```bash
docker-compose build frontend
docker-compose up -d frontend
```

Or in one command:

```bash
docker-compose up -d --build frontend
```

### Rebuild backend after code changes

```bash
docker-compose build app
docker-compose up -d app
```

Or in one command:

```bash
docker-compose up -d --build app
```

### Rebuild everything

```bash
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

---

## Development Workflow

### Option 1: All in Docker (Production-like)

✅ **Pros**: Matches production environment
❌ **Cons**: Need to rebuild after every frontend code change

```bash
docker-compose up -d
# Access frontend at http://localhost:3000
```

### Option 2: Local Frontend + Docker Backend (Recommended for Development)

✅ **Pros**: Hot reload on frontend changes, no rebuild needed
❌ **Cons**: Need npm installed locally

```bash
# Stop frontend container
docker-compose stop frontend

# Start frontend locally with hot reload
cd ../demo_front
npm install  # Only first time
npm run dev  # Start dev server

# Access frontend at http://localhost:5173
# Backend still runs in Docker at http://localhost:8080
```

---

## Viewing Logs

### View logs from all containers

```bash
docker-compose logs -f
```

### View logs from specific container

```bash
docker logs prediction-app -f         # Backend
docker logs prediction-frontend -f    # Frontend
docker logs prediction-mysql -f       # Database
docker logs prediction-phpmyadmin -f  # phpMyAdmin
```

### View last N lines of logs

```bash
docker logs prediction-app --tail 100
```

---

### Database Management

```bash
# Connect to MySQL via terminal
docker exec -it prediction-mysql mysql -uroot -proot betsdb

# Example queries
USE betsdb;
SHOW TABLES;
SELECT * FROM user LIMIT 10;
SELECT * FROM league;
```

---

## Docker Structure

### Docker Compose contains 4 services:

1. **mysql** (`prediction-mysql`)
   - Image: `mysql:8.0`
   - Port: `3306`
   - Database: `betsdb`
   - Initialization: Automatic via scripts in `/docker/mysql/init/`
   - Encoding: UTF-8 (utf8mb4)

2. **app** (`prediction-app`)
   - Built from `Dockerfile` (Spring Boot)
   - Port: `8080`
   - Depends on: `mysql` (waits for health check)
   - Automatically connects to the database

3. **frontend** (`prediction-frontend`)
   - Built from `../demo_front/Dockerfile` (React + Vite + nginx)
   - Port: `3000` (mapped to container port 80)
   - nginx serves static files and proxies `/api` requests to backend
   - Depends on: `app`

4. **phpmyadmin** (`prediction-phpmyadmin`)
   - Image: `phpmyadmin/phpmyadmin`
   - Port: `8081`
   - Web interface for MySQL management

### Database Initialization

The database is automatically initialized on first startup by SQL scripts in order:

1. `00-create-tables.sql` - Table creation
2. `02-worldcup-2026-countries-groups.sql` - Countries and groups data
3. `03-worldcup-2026-matches.sql` - World Cup 2026 matches
4. `04-users-only.sql` - Sample users
5. `05-poland-matches.sql` - Poland national team matches

---

## Troubleshooting

### Frontend shows gray overlay and can't click anything

If the frontend loads but shows a gray semi-transparent overlay blocking interaction:
- The LeagueSelector dropdown is open
- Click anywhere on the gray area to close it
- If problem persists, rebuild frontend: `docker-compose build frontend && docker-compose up -d frontend`

### Port already in use

```bash
# Check what's using the port
lsof -i :3000  # Frontend
lsof -i :8080  # Backend
lsof -i :3306  # MySQL

# Kill the process or stop the container
docker-compose down
```

### Database connection issues

```bash
# Check if MySQL is healthy
docker-compose ps

# Wait for MySQL to be healthy (may take 30-60 seconds)
docker logs prediction-mysql -f

# Restart backend if it started before MySQL was ready
docker-compose restart app
```

### Rebuild from scratch

```bash
# Remove everything including volumes
docker-compose down -v

# Rebuild without cache
docker-compose build --no-cache

# Start fresh
docker-compose up -d
```

---

## Application Structure

### Web Endpoints

- `/register` – User registration
- `/login` – User login
- `/games` – All games list
- `/ranking` – User ranking
- `/predictions/new/..` - Create predictions
- `/predictions/edit/..` - Update predictions
- `/results/..` - All users' predictions and points for a specific game
- `/comments` - Comment section

### Admin Panel

- `/admin/games` - Game management
- `/admin/games/new` - Add game
- `/admin/games/edit/..` - Update game

### REST API

- `/swagger-ui/index.html` - REST API documentation

---

## Key Functionality

### Game Management (Administrator)
- Add, update, delete games
- Deletion is only allowed for games with the ADMIN_VIEW status
- Validation ensures correct date and status when creating or updating a game
- Changing a game's status to FINISHED automatically triggers the calculation of prediction points

![img_7.png](docs/img_7.png)

### Game Score Prediction
- Users can view a list of games with SCHEDULED and FINISHED statuses
- For SCHEDULED games, users can submit score predictions
- Predictions can be updated multiple times before the game start time
- After the admin changes the game status to FINISHED, points are calculated:
  - **3 points** - exact score prediction
  - **1 point** - correct winner/draw prediction with wrong score
  - **0 points** - incorrect prediction

![img_12.png](docs/img_12.png)

![img_13.png](docs/img_13.png)

### Ranking System
- Rankings are updated after each finished game based on points earned from predictions
- Displays each user's current position and changes compared to the previous round (promotions/demotions)

![img_4.png](docs/img_4.png)

![img_5.png](docs/img_5.png)

### Match Status
- Every match must have a defined status at all times
- Validation prevents adding results unless the match status is set to FINISHED

### Comment Section
- Users can post comments visible to all participants

![img.png](docs/img.png)

---

## API Endpoints

| Method   | Endpoint                                           | Description                                       |
|----------|----------------------------------------------------|---------------------------------------------------|
| `GET`    | `/api/admin/games`                                 | Get all games visible for admin                   |
| `GET`    | `/api/admin/games/{id}`                            | Get game by ID                                    |
| `POST`   | `/api/admin/games`                                 | Create new game                                   |
| `PUT`    | `/api/admin/games/{id}`                            | Update game                                       |
| `DELETE` | `/api/admin/games/{id}`                            | Delete game                                       |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/user`                                        | Get all users                                     |
| `GET`    | `/api/user/{id}`                                   | Get user by ID                                    |
| `POST`   | `/api/user`                                        | Create new user                                   |
| `PUT`    | `/api/user`                                        | Update user password                              |
| `DELETE` | `/api/user/{id}`                                   | Delete user                                       |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/games`                                       | Get all games                                     |
| `GET`    | `/api/games/{id}`                                  | Get game by ID                                    |
| `GET`    | `/api/games/upcoming`                              | Get all games with status SCHEDULED               |
| `GET`    | `/api/games/finished`                              | Get all games with status FINISHED                |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/predictions/my`                              | Get all predictions for logged user               |
| `GET`    | `/api/predictions/{id}`                            | Get prediction by ID                              |
| `POST`   | `/api/predictions`                                 | Create new prediction                             |
| `PUT`    | `/api/predictions/{id}`                            | Update prediction                                 |
| `DELETE` | `/api/predictions/{id}`                            | Delete prediction by ID                           |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/results/my-prediction-result`                | Get all predictions with result for logged user   |
| `GET`    | `/api/results/allusers-prediction-result/{gameId}` | Get all predictions with result for one game      |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/ranking`                                     | Get ranking                                       |
| -        | -                                                  | -                                                 |
| `GET`    | `/api/comments`                                    | Get all comments                                  |
| `POST`   | `/api/comments`                                    | Create new comment                                |

---

## Roadmap
- Enhanced dashboard with statistics
