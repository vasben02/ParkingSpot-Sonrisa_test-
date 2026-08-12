# Decision Log & Reflection

## Decision Log
| Decision point | What I chose | Why | What alternative I rejected |
| :--- | :--- | :--- | :--- |
| **Database choice** | PostgreSQL inside Docker | Provides strong relational integrity for time/date logic and fulfills the single-command startup requirement easily. | In-memory H2 database (rejected because the assignment required a persistent, initialized database state). |
| **Time-overlap validation** | Handled in the Java Service layer | It allows for easier Unit Testing (via Mockito) without needing a live database connection. | Handling overlaps via complex SQL constraints/triggers (rejected because it hides business logic from the application). |
| **Containerization** | Multi-stage Dockerfile | Compiles the Java app via Maven in Stage 1, then runs it on a lightweight JRE in Stage 2 to save container size. | Pushing the pre-compiled `.jar` file manually (rejected as it doesn't guarantee a clean build on different machines). |
| **Missing IDs in JSON** | Changed `int id` to `Integer id` in the Model | Spring Boot threw 400 errors because primitive `int` cannot be null when accepting POST requests without an ID. | Requiring users to send a fake `id: 0` in their request (rejected as bad API design). |

## Short Summary
The biggest challenge during implementation was ensuring the Docker network properly connected the Spring Boot container to the PostgreSQL container. I encountered an `UnknownHostException` when the database crashed due to Docker misinterpreting my `schema.sql` file as an empty folder named `init.sql`. Once I corrected the volume mapping in `docker-compose.yml`, the application successfully booted in a single command. Writing the Mockito unit tests also helped me solidify how the business logic handles edge cases without needing a real database.

## Use of AI Tools
I used an AI assistant as an interactive mentor throughout this project. Instead of asking it to write the entire project at once, I used it to guide me step-by-step using an MVP (Minimum Viable Product) approach. It helped me structure my `docker-compose.yml`, debug Spring Boot deserialization errors (like the `int` vs `Integer` issue), and write the Mockito unit tests for the time-overlap logic.