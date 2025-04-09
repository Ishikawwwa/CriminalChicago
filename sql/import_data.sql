START TRANSACTION;

CREATE TEMPORARY TABLE crimes_staging (
    dummy_column TEXT,
    id TEXT,
    case_number TEXT,
    date TEXT,
    block TEXT,
    iucr TEXT,
    primary_type TEXT,
    description TEXT,
    location_description TEXT,
    arrest TEXT,
    domestic TEXT,
    beat TEXT,
    district TEXT,
    ward TEXT,
    community_area TEXT,
    fbi_code TEXT,
    x_coordinate TEXT,
    y_coordinate TEXT,
    year TEXT,
    updated_on TEXT,
    latitude TEXT,
    longitude TEXT,
    location TEXT
);

COPY crimes_staging FROM STDIN WITH (FORMAT CSV, HEADER, NULL '');

INSERT INTO crimes (
    id, date, block, primary_type, location_description,
    arrest, domestic, beat, district, ward,
    community_area, fbi_code, x_coordinate, y_coordinate
)
SELECT 
    NULLIF(id, '')::INTEGER,
    NULLIF(date, '')::TIMESTAMP,
    NULLIF(block, ''),
    NULLIF(primary_type, ''),
    NULLIF(location_description, ''),
    NULLIF(arrest, '')::BOOLEAN,
    NULLIF(domestic, '')::BOOLEAN,
    NULLIF(beat, '')::INTEGER,
    NULLIF(district, '')::INTEGER,
    NULLIF(ward, '')::FLOAT,
    NULLIF(community_area, '')::FLOAT,
    NULLIF(fbi_code, ''),
    NULLIF(x_coordinate, '')::FLOAT,
    NULLIF(y_coordinate, '')::FLOAT
FROM crimes_staging
WHERE id IS NOT NULL AND primary_type IS NOT NULL
ON CONFLICT (id) DO NOTHING;

DROP TABLE crimes_staging;

COMMIT;
