# Parking Reservation System

## 1. System Design
This backend is built using **Java 17**, **Spring Boot**, and **PostgreSQL**.
- **Architecture**: It follows a standard N-Tier architecture (Controller -> Service -> Repository -> Database) to ensure clear separation of concerns.
- **Performance Considerations**:
    - Validations (like time-overlap checks) are handled in the Java application layer to reduce database load and complex SQL locks.
    - Foreign keys (`ON DELETE CASCADE`) and Primary Keys are indexed by default in PostgreSQL for fast lookups.
    - Connection pooling is handled via HikariCP (Spring Boot's default) to manage concurrent database requests efficiently.
- **Optional Extra**: Implemented `space_type` in the database (`STANDARD`, `EV_CHARGER`, `VIP`) to allow future scaling of reservation rules based on vehicle requirements.

## 2. API Description
The REST API accepts and returns JSON.
- `GET /api/reservations/space/{spaceId}` : Returns a list of all active reservations for a specific parking space.
- `POST /api/reservations` : Creates a new reservation. Requires a JSON body with `spaceId`, `requesterName`, `startTime`, and `endTime` (ISO-8601 format). Validates against overlapping times.
- `DELETE /api/reservations/{id}` : Cancels a reservation by its ID.

## 3. User Manual
**Prerequisites:** Docker and Docker Compose installed.
1. Clone this repository.
2. Open a terminal in the root directory.
3. Run the following command:
   `docker-compose up --build -d`
4. The system will automatically initialize the PostgreSQL database with default parking spaces and start the Java application on `localhost:8080`.
5. You can test the endpoints using the provided `test.http` file in the project root, or via Postman.