declare
    person_sport_count number;
begin
    select count(1)
    into person_sport_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'PERSON_SPORT';
    if (0 = person_sport_count)
    then
        execute immediate
            'create table person_sport
             (
                 id_person number not null,
                 sport varchar(10) not null,
                 category number not null,
                 CONSTRAINT fk_person_sport
                     FOREIGN KEY (sport)
                         REFERENCES SPORT(name),
                 CONSTRAINT fk_person
                     FOREIGN KEY (id_person)
                         REFERENCES PERSON(id),
                 CONSTRAINT person_sport_pk PRIMARY KEY (id_person, sport)
             )';
    end if;
end;

create or replace trigger tr_person_sport_insert
    before insert or update
    on person_sport
    for each row
declare kind_person varchar(100);
begin
    select kind
    into kind_person
    from PERSON where
            PERSON.ID = :new.ID_PERSON;
    if (kind_person != 'SPORTSMAN')
    then
        RAISE_APPLICATION_ERROR( -20001,
                                 'Person must be sportsman' );
    end if;
end;