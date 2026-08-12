-- Create the parking spaces table
CREATE TABLE parking_spaces (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(50) NOT NULL UNIQUE,
                                space_type VARCHAR(50) DEFAULT 'STANDARD' -- Ready for the optional extra (e.g., 'EV_CHARGER', 'VIP')
);

-- Create the reservations table
CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              space_id INT NOT NULL,
                              requester_name VARCHAR(100) NOT NULL,
                              start_time TIMESTAMP NOT NULL,
                              end_time TIMESTAMP NOT NULL,
                              CONSTRAINT fk_space
                                  FOREIGN KEY(space_id)
                                      REFERENCES parking_spaces(id)
                                      ON DELETE CASCADE
);

-- Pre-populate the database with some initial parking spaces
INSERT INTO parking_spaces (name, space_type) VALUES
                                                  ('A1', 'STANDARD'),
                                                  ('A2', 'STANDARD'),
                                                  ('B1', 'EV_CHARGER'),
                                                  ('B2', 'VIP');