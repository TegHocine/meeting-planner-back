CREATE  TABLE meeting_equipment(
    meeting_id UUID NOT NULL,
    equipment_id UUID NOT NULL,
    CONSTRAINT pk_meeting_equipment PRIMARY KEY (meeting_id,equipment_id)
)