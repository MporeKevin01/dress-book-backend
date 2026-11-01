# Dress Book Backend

## Project information

- **Project name:** Dress Book Backend
- **Student name:** UFITEYEZU Mpore Kevin
- **Student ID:** 21831
- **Repository:** [MporeKevin01/dress-book-backend](https://github.com/MporeKevin01/dress-book-backend)

## Requirements

- Java 25
- PostgreSQL

## Run the API

1. Clone the repository and open a terminal in the project directory.
2. Create a PostgreSQL database named `dressbook`.
3. Set `DB_PASSWORD` to the password for the PostgreSQL user configured in `src/main/resources/application.properties` (default username: `postgres`).

   **PowerShell:**
   ```powershell
   $env:DB_PASSWORD = "your-postgres-password"
   ```

   **macOS/Linux:**
   ```bash
   export DB_PASSWORD="your-postgres-password"
   ```

4. Start the API with the Maven wrapper:

   **Windows:**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **macOS/Linux:**
   ```bash
   ./mvnw spring-boot:run
   ```

The API listens on port `9090`. Its REST endpoints are under `/api` (for example, `/api/customers` and `/api/tailoring-orders`).

## Run tests

**Windows:**
```powershell
.\mvnw.cmd test
```

**macOS/Linux:**
```bash
./mvnw test
```
