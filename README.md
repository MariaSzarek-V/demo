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

### Check if Docker is installed

```bash
docker --version
docker-compose --version
```

If Docker is not installed, download it from [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

---

### Step 1: Clone the repository

```bash
git clone https://github.com/your-repo/PredictionCup.git
cd PredictionCup
```

---

### Step 2: Start the application

The application uses Docker Compose which automatically:
- Builds the Spring Boot application
- Creates MySQL database
- Initializes the database with sample data
- Starts phpMyAdmin for database management

**Start all containers:**

```bash
docker-compose up -d
```

---

### Step 3: Wait for the application to start

The application needs approximately **60-90 seconds** to fully start. You can monitor the logs:

```bash
# Check container status
docker-compose ps

# View application logs
docker logs prediction-app -f

# View database logs
docker logs prediction-mysql -f
```

The application is ready when you see in the logs:
```
Started DemoApplication in X.XXX seconds
```

---

## Access the Application

After starting the application, you can access it at the following addresses:

### 🌐 Main Application
- **URL**: [http://localhost:8080/games](http://localhost:8080/games)
- **Games List**: [http://localhost:8080/games](http://localhost:8080/games)
- **Ranking**: [http://localhost:8080/ranking](http://localhost:8080/ranking)
- **Comments**: [http://localhost:8080/comments](http://localhost:8080/comments)

### 📚 API Documentation (Swagger)
- **URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Managing Containers

### Stop the application

```bash
docker-compose stop
```

### Restart the application

```bash
docker-compose start
```

### Stop and remove containers

```bash
docker-compose down
```

### Stop and remove containers with data (removes database!)

```bash
docker-compose down -v
```

⚠️ **WARNING**: This command will delete all data from the database!

---

### Rebuild the application after code changes

If you made changes to the source code:

```bash
# Stop and remove containers
docker-compose down

# Rebuild the application image
docker-compose build --no-cache app

# Start again
docker-compose up -d
```

Or do everything with one command:

```bash
docker-compose up -d --build
```
---

### Database Management

```bash
# Connect to MySQL via terminal
docker exec -it prediction-mysql mysql -uroot -proot betsdb
```
---

## Docker Structure

### Docker Compose contains 3 services:

1. **mysql** (`prediction-mysql`)
   - Image: `mysql:8.0`
   - Port: `3306`
   - Database: `betsdb`
   - Initialization: Automatic via scripts in `/docker/mysql/init/`
   - Encoding: UTF-8 (utf8mb4)

2. **app** (`prediction-app`)
   - Built from `Dockerfile`
   - Port: `8080`
   - Depends on: `mysql` (waits for health check)
   - Automatically connects to the database

3. **phpmyadmin** (`prediction-phpmyadmin`)
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
