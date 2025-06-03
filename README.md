# NSF Demo - Stock Trading Microservices

A demonstration project showcasing NetEase Service Framework (NSF) capabilities through a stock trading application built with Spring Boot microservices.

## Overview

This project consists of three interconnected microservices that demonstrate service discovery, monitoring, and communication patterns using NetEase's NSF framework:

- **Stock Provider** - Provides stock data and market information
- **Stock Viewer** - Displays and visualizes stock information  
- **Stock Advisor** - Offers stock recommendations and analysis

## Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Stock Advisor  │    │  Stock Viewer   │    │ Stock Provider  │
│                 │    │                 │    │                 │
│ Port: 8080      │◄──►│ Port: 8080      │◄──►│ Port: 8080      │
│ Recommendations │    │ UI & Display    │    │ Market Data     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   NSF Server    │
                    │ Service Registry│
                    │   & Monitoring  │
                    └─────────────────┘
```

## Project Structure

```
nsf-demo/
├── pom.xml                           # Parent Maven configuration
├── nsf-demo-stock-provider/          # Stock data provider service
│   ├── src/main/java/                # Java source code
│   ├── pom.xml                       # Module dependencies
│   ├── nsf.yml                       # NSF configuration
│   ├── Dockerfile                    # Container configuration
│   └── run.sh                        # Service startup script
├── nsf-demo-stock-viewer/            # Stock viewing service
│   ├── src/main/java/                # Java source code
│   ├── pom.xml                       # Module dependencies
│   ├── nsf.yml                       # NSF configuration
│   ├── Dockerfile                    # Container configuration
│   └── run.sh                        # Service startup script
└── nsf-demo-stock-advisor/           # Stock advisory service
    ├── src/main/java/                # Java source code
    ├── pom.xml                       # Module dependencies
    ├── nsf.yml                       # NSF configuration
    ├── Dockerfile                    # Container configuration
    └── run.sh                        # Service startup script
```

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- Docker (for containerized deployment)
- Access to NSF Server (configured in [nsf.yml](nsf-demo-stock-provider/nsf.yml) files)

## Getting Started

### Building the Project

Build all modules from the root directory:

```bash
mvn clean install
```

### Running Services Locally

#### 1. Start Stock Provider Service

```bash
cd nsf-demo-stock-provider
mvn spring-boot:run
```

#### 2. Start Stock Viewer Service

```bash
cd nsf-demo-stock-viewer
mvn spring-boot:run
```

#### 3. Start Stock Advisor Service

```bash
cd nsf-demo-stock-advisor
mvn spring-boot:run
```

### Running with Docker

#### Build Docker Images

For each service, build the Docker image:

```bash
# Stock Provider
cd nsf-demo-stock-provider
docker build -t nsf-demo-stock-provider .

# Stock Viewer  
cd nsf-demo-stock-viewer
docker build -t nsf-demo-stock-viewer .

# Stock Advisor
cd nsf-demo-stock-advisor
docker build -t nsf-demo-stock-advisor .
```

#### Run Containers

```bash
# Run Stock Provider
docker run -p 8080:8080 nsf-demo-stock-provider

# Run Stock Viewer (use different port)
docker run -p 8081:8080 nsf-demo-stock-viewer

# Run Stock Advisor (use different port)
docker run -p 8082:8080 nsf-demo-stock-advisor
```

## API Documentation

### Stock Provider Service

The Stock Provider service exposes the following REST endpoints:

#### Get All Stocks
```http
GET /stocks
```

**Parameters:**
- `delay` (optional): Delay in seconds before response (default: 0)

#### Get Stocks by IDs
```http
GET /stocks/{stockIds}
```

**Parameters:**
- `stockIds`: Comma-separated list of stock IDs

#### Health Check
```http
GET /health
```

#### Service Info
```http
GET /hi
GET /echo
```

### Example Stock Data Structure

```json
{
  "id": "AAPL",
  "name": "Apple Inc.",
  "dailyKLineAddr": "https://example.com/chart.png",
  "openingPrice": "150.00",
  "closingPrice": "152.50",
  "currentPrice": "151.75",
  "inPrice": "151.50",
  "outPrice": "152.00",
  "topTodayPrice": "153.00",
  "bottomTodayPrice": "149.50"
}
```

## Configuration

### NSF Configuration

Each service includes an [`nsf.yml`](nsf-demo-stock-provider/nsf.yml) configuration file that defines:

- Service registration details
- NSF server connection
- Application monitoring settings
- Security credentials

### Maven Configuration

The project uses a multi-module Maven structure defined in the root [`pom.xml`](pom.xml):

- **Parent**: Spring Boot 2.1.6.RELEASE
- **Java Version**: 1.8
- **Modules**: stock-provider, stock-viewer, stock-advisor

## Monitoring and Observability

The project integrates with NetEase's monitoring stack:

- **NSF Agent**: Service discovery and management
- **APM Agent**: Application performance monitoring
- **Custom Metrics**: JVM and application-specific metrics

## Development

### Adding New Features

1. Implement new functionality in the appropriate service module
2. Update the corresponding [`pom.xml`](pom.xml) if new dependencies are needed
3. Modify [`nsf.yml`](nsf-demo-stock-provider/nsf.yml) configuration if required
4. Update Docker configuration in [`Dockerfile`](nsf-demo-stock-provider/Dockerfile)
5. Test locally before building Docker images

### Code Structure

Each service follows standard Spring Boot conventions:

```
src/main/java/com/netease/cloud/nsf/demo/stock/{service}/
├── {Service}Application.java          # Main application class
└── web/
    ├── controller/                    # REST controllers
    ├── service/                       # Business logic
    ├── entity/                        # Data models
    └── util/                          # Utility classes
```

## Troubleshooting

### Common Issues

1. **Service Registration Failed**: Check NSF server connectivity and credentials in [`nsf.yml`](nsf-demo-stock-provider/nsf.yml)
2. **Port Conflicts**: Ensure each service runs on a different port when testing locally
3. **Build Failures**: Verify Java 8+ and Maven 3.6+ are installed

### Logs

Check application logs for detailed error information:

```bash
# For local runs
tail -f logs/application.log

# For Docker containers
docker logs <container-name>
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is a demonstration application for NetEase Service Framework.