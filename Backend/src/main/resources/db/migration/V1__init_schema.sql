CREATE TABLE "agency" (
  "id" integer PRIMARY KEY,
  "name" varchar NOT NULL,
  "service_area" varchar,
  "gtfs_agency_id" varchar UNIQUE NOT NULL
);

CREATE TABLE "route" (
  "id" integer PRIMARY KEY,
  "agency_id" integer NOT NULL,
  "short_name" varchar NOT NULL,
  "route_type" integer NOT NULL,
  "gtfs_route_id" varchar UNIQUE NOT NULL
);

CREATE TABLE "stop" (
  "id" integer PRIMARY KEY,
  "gtfs_stop_id" varchar UNIQUE NOT NULL,
  "name" varchar NOT NULL,
  "lat" decimal NOT NULL,
  "lon" decimal NOT NULL
);

CREATE TABLE "trip" (
  "id" integer PRIMARY KEY,
  "route_id" integer NOT NULL,
  "gtfs_trip_id" varchar UNIQUE NOT NULL,
  "headsign" varchar,
  "direction_id" integer
);

CREATE TABLE "stop_time" (
  "id" integer PRIMARY KEY,
  "trip_id" integer NOT NULL,
  "stop_id" integer NOT NULL,
  "stop_sequence" integer NOT NULL,
  "scheduled_arrival_sec" integer,
  "scheduled_departure_sec" integer
);

CREATE TABLE "delay_observation" (
  "id" bigint PRIMARY KEY,
  "trip_id" integer NOT NULL,
  "stop_id" integer NOT NULL,
  "service_date" date NOT NULL,
  "delay_seconds" integer,
  "fetched_at" timestamptz NOT NULL
);

CREATE UNIQUE INDEX ON "stop_time" ("trip_id", "stop_sequence");

CREATE UNIQUE INDEX ON "delay_observation" ("trip_id", "stop_id", "service_date");

CREATE INDEX ON "delay_observation" ("fetched_at");

ALTER TABLE "route" ADD CONSTRAINT "agency_routes" FOREIGN KEY ("agency_id") REFERENCES "agency" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "trip" ADD CONSTRAINT "route_trips" FOREIGN KEY ("route_id") REFERENCES "route" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "stop_time" ADD CONSTRAINT "trip_stop_times" FOREIGN KEY ("trip_id") REFERENCES "trip" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "stop_time" ADD CONSTRAINT "stop_stop_times" FOREIGN KEY ("stop_id") REFERENCES "stop" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "delay_observation" ADD CONSTRAINT "trip_delays" FOREIGN KEY ("trip_id") REFERENCES "trip" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "delay_observation" ADD CONSTRAINT "stop_delays" FOREIGN KEY ("stop_id") REFERENCES "stop" ("id") DEFERRABLE INITIALLY IMMEDIATE;
