ALTER TABLE meeting DROP CONSTRAINT check_type;
ALTER TABLE meeting ADD CONSTRAINT check_type CHECK ( type in ('VC','SPEC','RS','RC')  )