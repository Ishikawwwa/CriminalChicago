START TRANSACTION;

DROP TABLE IF EXISTS crimes CASCADE;

CREATE TABLE crimes (
    id INTEGER NOT NULL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    block VARCHAR(255) NOT NULL,
    primary_type VARCHAR(255) NOT NULL,
    location_description VARCHAR(255),
    arrest BOOLEAN NOT NULL,
    domestic BOOLEAN NOT NULL,
    beat INTEGER,
    district INTEGER,
    ward FLOAT,
    community_area FLOAT,
    fbi_code VARCHAR(10),
    x_coordinate FLOAT,
    y_coordinate FLOAT
);

CREATE INDEX IF NOT EXISTS idx_crimes_date ON crimes(date);
CREATE INDEX IF NOT EXISTS idx_crimes_type ON crimes(primary_type);
CREATE INDEX IF NOT EXISTS idx_crimes_location ON crimes(location_description);

COMMIT;
