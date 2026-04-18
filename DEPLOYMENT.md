# Deployment Guide

## Remote Access Configuration

By default, the application only accepts connections from `localhost`. To enable remote access:

### 1. Create `.env` file

```bash
cp .env.example .env
```

### 2. Configure CORS Origins

Edit `.env` and add your server's IP address(es):

```bash
# For single server
CORS_ALLOWED_ORIGINS=http://192.168.0.18:3000

# For multiple servers
CORS_ALLOWED_ORIGINS=http://192.168.0.18:3000,http://192.168.1.100:3000,http://my-server.local:3000
```

### 3. Restart the application

```bash
docker-compose down
docker-compose up -d
```

## Quick Start

### Local Development (default)

```bash
docker-compose up -d
```

Access: `http://localhost:3000`

### Remote Server

1. Find your server IP:
```bash
ip addr show | grep "inet " | grep -v 127.0.0.1
# or
hostname -I
```

2. Create `.env` file:
```bash
echo "CORS_ALLOWED_ORIGINS=http://YOUR_SERVER_IP:3000" > .env
```

3. Start application:
```bash
docker-compose up -d
```

4. Access from any device: `http://YOUR_SERVER_IP:3000`

## Troubleshooting

### Login fails with "Invalid username or password"

Check if CORS is properly configured:

```bash
# Check if CORS environment variable is set
docker exec prediction-app printenv | grep CORS

# Check backend logs
docker logs prediction-app | tail -50
```

### Connection refused

Ensure firewall allows connections on port 3000:

```bash
# Ubuntu/Debian
sudo ufw allow 3000/tcp

# CentOS/RHEL
sudo firewall-cmd --add-port=3000/tcp --permanent
sudo firewall-cmd --reload
```

## Architecture

- **Frontend (React)**: Port 3000 - NGINX serving static files + API proxy
- **Backend (Spring Boot)**: Port 8080 - REST API
- **Database (MySQL)**: Port 3306 - Data storage

The frontend NGINX automatically proxies `/api` requests to the backend, so no additional configuration is needed.
