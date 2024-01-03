CREATE  TABLE room_equipment(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL default 1,
    room_id UUID,
    CONSTRAINT fk_room_id FOREIGN KEY(room_id) REFERENCES room(id),
    CONSTRAINT check_name CHECK ( name IN ('ECRAN', 'NEANT', 'TABLEAU', 'WEBCAM', 'PIEUVRE') )
)