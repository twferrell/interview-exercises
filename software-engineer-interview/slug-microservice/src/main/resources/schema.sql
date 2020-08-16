CREATE TABLE site_data.slugs (
  id            VARCHAR PRIMARY KEY,
  url           VARCHAR NOT NULL
);

CREATE TABLE site_data.slug_descriptions (
  id            INT IDENTITY PRIMARY KEY,
  slug_id       VARCHAR REFERENCES site_data.slugs (id) ON DELETE CASCADE,
  description   VARCHAR NOT NULL
);