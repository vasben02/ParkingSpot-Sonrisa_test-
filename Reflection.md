# Decision Log & Reflection

## Decision Log
| Decision point | What I chose | Why | What alternative I rejected |
| :--- | :--- | :--- | :--- |
| **Database setup** | PostgreSQL via Docker Compose | AI recommended this to fulfill the "single-command startup" requirement easily while providing a real relational database. | In-memory H2 database (rejected because the system required a persistent, initialized database state). |
| **Time-overlap validation** | Handled in the Java Service layer | The AI explained that validating business logic in Java allows for easier Unit Testing (via Mockito) without needing a live database connection. | Handling overlaps via complex SQL constraints (rejected to keep business logic centralized in the app). |
| **Fixing 400 Bad Request** | Changed `int id` to `Integer id` in the Java Models | During testing, Spring Boot crashed because it couldn't map a missing ID in the JSON POST request to a primitive `int`. `Integer` allows nulls. | Forcing the user to pass a dummy `id: 0` in the JSON (rejected as bad API design). |
| **Resolving 500 Error** | Renamed `schema.sql` and fixed Docker volume mapping | Docker created an empty folder instead of reading my SQL file, causing the DB to crash. I traced the logs to find the volume naming mismatch. | Ignoring the Docker logs and blindly restarting (rejected because it wouldn't fix the underlying root cause). |

## Short Summary
To be completely transparent, this assignment was a massive reality check. I restarted the project 4-5 times because while university taught me Java and OOP concepts, I had no practical experience with industry tools like Spring Boot, Docker, or Mockito. My biggest challenge was bridging the gap between academic knowledge and actual backend infrastructure. However, by treating this as a hands-on learning experience, I learned how to actually debug a system—specifically, how to read Docker stack traces, fix container crashes caused by file mapping errors, and understand how a REST controller talks to a database.

## Use of AI Tools
I used an AI assistant extensively throughout this project, essentially treating it as a senior mentor and pair-programmer. Because the frameworks were entirely new to me, I relied on the AI to generate the initial Spring Boot code, the Mockito test structures, and the Docker configuration files.

While the AI wrote the bulk of the syntax, my role was driving the architecture, assembling the pieces, and debugging. When the application threw 400 and 500 errors, I was the one who had to pull the server logs, inspect the Docker crash reports, and work with the AI to find the root causes (like the `schema.sql` naming conflict). I am submitting my raw chat history to show this exact learning process. I may not have known these tools yesterday, but this process taught me how they actually work together.