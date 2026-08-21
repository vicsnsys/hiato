DROP TABLE IF EXISTS artist_tracks;

CREATE TABLE artist_tracks (
                               artist_id BIGINT NOT NULL,
                               track_id BIGINT NOT NULL,
                               is_primary BOOLEAN NOT NULL,
                               PRIMARY KEY (artist_id, track_id),

                               CONSTRAINT artist_tracks_artist_id_fkey
                                   FOREIGN KEY (artist_id)
                                       REFERENCES artists(id),

                               CONSTRAINT artist_tracks_track_id_fkey
                                   FOREIGN KEY (track_id)
                                       REFERENCES tracks(id)
);