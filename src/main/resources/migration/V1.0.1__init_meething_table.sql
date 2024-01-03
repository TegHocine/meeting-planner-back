CREATE TABLE meeting(
    id UUID NOT NULL PRIMARY KEY ,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    nbr_people INT NOT NULL,
    type VARCHAR(50) NOT NULL ,
    room_id UUID,
    CONSTRAINT fk_room_id FOREIGN KEY(room_id) REFERENCES room(id),
    CONSTRAINT check_type CHECK ( type in ('VS','SPEC','RS','RC') )
);