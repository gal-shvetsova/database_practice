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
            'create table sportsman_characteristic
             (
                 id_sportsman varchar2(100) not null,
                 sport varchar2(100) not null,
                 category number not null,
                 id_trainer varchar2(100) not null,
                club varchar2(100) not null,
                 CONSTRAINT fk_sc_sport
                     FOREIGN KEY (sport)
                         REFERENCES SPORT(name),
                 CONSTRAINT fk_sc_sportsman
                     FOREIGN KEY (id_sportsman)
                         REFERENCES PERSON(id),
                 CONSTRAINT fk_sc_trainer
                     FOREIGN KEY (id_trainer)
                         REFERENCES PERSON(id),
                 CONSTRAINT person_sport_pk PRIMARY KEY (id_sportsman, sport)
             )';
    end if;
end;

create or replace trigger tr_person_sport_insert
    before insert or update
    on sportsman_characteristic
    for each row
declare role_sportsman varchar(100);
    role_trainer varchar(100);
begin
    select role
    into role_sportsman
    from PERSON where
            PERSON.ID = :new.id_sportsman;
    select role
    into role_trainer
    from PERSON where
            PERSON.ID = :new.id_trainer;
    if (role_sportsman != 'SPORTSMAN' or role_trainer != 'TRAINER')
    then
        RAISE_APPLICATION_ERROR( -20001,
                                 'First person must be sportsman, second must be trainer' );
    end if;
end;