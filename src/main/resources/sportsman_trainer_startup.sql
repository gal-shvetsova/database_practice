declare sportsman_trainer_count number;
begin
    select count(1)
    into sportsman_trainer_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'SPORTSMAN_TRAINER';
    if (0 = sportsman_trainer_count)
    then
        execute immediate
            'create table sportsman_trainer
             (
                 id_sportsman number unique not null,
                 id_trainer number not null,
                 constraint sportsman_trainer_pk PRIMARY KEY (id_sportsman, id_trainer),
                 CONSTRAINT fk_sportsman FOREIGN KEY (id_sportsman) references PERSON(id),
                 constraint fk_trainer foreign key (id_trainer) references PERSON(id)
             )';
    end if;
end;

create or replace trigger tr_sportsman_trainer_insert
    before insert or update
    on sportsman_trainer
    for each row
declare role_sportsman varchar(100);
        role_trainer varchar(100);
begin
    select role
    into role_sportsman
    from PERSON where
            PERSON.ID = :new.ID_SPORTSMAN;
    select role
    into role_trainer
    from PERSON where   PERSON.ID = :new.ID_TRAINER;

    if (role_sportsman != 'SPORTSMAN' or role_trainer != 'TRAINER')
    then
        RAISE_APPLICATION_ERROR( -20001,
                                 'First person must be sportsman, second person must be trainer' );
    end if;
end;