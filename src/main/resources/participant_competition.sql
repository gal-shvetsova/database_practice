declare participant_competition_count number;
begin
    select count(1)
    into participant_competition_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'PARTICIPANT_COMPETITION';
    if (0 = participant_competition_count)
    then
        execute immediate
            'create table PARTICIPANT_COMPETITION
             (
                 id_participant number not null,
                 id_competition number unique not null,
                 result number not null,
                 constraint PARTICIPANT_COMPETITION_PK PRIMARY KEY (id_participant, id_competition),
                 CONSTRAINT fk_participant FOREIGN KEY (id_participant) references PERSON(id),
                 constraint fk_partic_competition foreign key (id_competition) references competition(id)
             )';
    end if;
end;

create or replace trigger tr_participant_competition
    before insert or update
    on PARTICIPANT_COMPETITION
    for each row
declare
    role_person varchar(100);
    competition_exist number;
begin
    select role
    into role_person
    from PERSON where
            PERSON.ID = :new.ID_PARTICIPANT;
    select count(1)
    into competition_exist
    from COMPETITION where COMPETITION.ID = :new.ID_COMPETITION;

    if (role_person != 'SPORTSMAN' or competition_exist = 0)
    then
        RAISE_APPLICATION_ERROR( -20001,
                                 'Person should be sportsman, competition must exist' );
    end if;
end;