declare organizer_competition_count number;
begin
    select count(1)
    into organizer_competition_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'ORGANIZER_COMPETITION';
    if (0 = organizer_competition_count)
    then
        execute immediate
            'create table ORGANIZER_COMPETITION
             (
                 id_organizer number not null,
                 id_competition number unique not null,
                 constraint ORGANIZER_COMPETITION_PK PRIMARY KEY (id_organizer, id_competition),
                 CONSTRAINT fk_organizer FOREIGN KEY (id_organizer) references PERSON(id),
                 constraint fk_competition foreign key (id_competition) references competition(id)
             )';
    end if;
end;

create or replace trigger tr_organizer_competition
    before insert or update
    on ORGANIZER_COMPETITION
    for each row
declare
    role_person varchar(100);
begin
    select role
    into role_person
    from PERSON where
            PERSON.ID = :new.ID_ORGANIZER;
    if (role_person != 'ORGANIZER')
    then
        RAISE_APPLICATION_ERROR( -20001,
                                 'Person should be organizer, competition must exist' );
    end if;
end;