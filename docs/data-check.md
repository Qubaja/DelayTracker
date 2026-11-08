# Data Check (Week 2, Task 6)

Goal: find out if the gtfs.de data is good enough for the collector in week 3, or if we need the DB Timetables API instead.

## Static data
- Source: https://download.gtfs.de/germany/nv_free/latest.zip
- Files open fine with pandas (`stops.txt`, `routes.txt`, `trips.txt`, `agency.txt`, ...).
- Germany-wide, ~692k stop rows. In our Ruhr bounding box we found about 8,800 stations (see `ml/explore_stops.ipynb`).

## Realtime data
- Source: https://realtime.gtfs.de/realtime-free.pb
- Format is GTFS-RT (protobuf), we read it with `gtfs-realtime-bindings`.
- Only TripUpdates (delays), no vehicle positions.
- The feed is rebuilt once per minute (header timestamp always ends on :49). Polling more often than every 60 s makes no sense.
- There is only one nationwide file, no regional version. One download has around 300k-750k stop rows depending on the time of day, so we have to filter the Ruhr area ourselves.
- Delays are in seconds (positive = late, negative = early). Some stops have no arrival/departure info.

## Ruhr coverage (Bogestra as test)
- Bogestra is `agency_id` 474 in `agency.txt`.
- 190 routes and 25,032 scheduled trips in the static data.
- In one realtime download (Sunday afternoon): 700 Bogestra trips with 4,490 stop rows, with real delay values.
- `trip_id`s from the realtime feed match the ones in `trips.txt`.

## Decision
We use gtfs.de. The data is usable and covers the Ruhr area. The DB Timetables API stays as a fallback.

For the collector (week 3):
- poll every 60 s
- filter Ruhr trips via agency -> routes -> trips
- note: `trip_id` is text in the realtime feed and a number in pandas by default, so convert before comparing
