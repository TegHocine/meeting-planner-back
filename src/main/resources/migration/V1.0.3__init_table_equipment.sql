CREATE  TABLE equipment(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL default 1,
    CONSTRAINT check_name CHECK ( name IN ('ECRAN', 'NEANT', 'TABLEAU', 'WEBCAM', 'PIEUVRE') )
)