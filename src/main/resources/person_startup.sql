declare
    person_count number;
begin
    select count(1)
    into person_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'PERSON';
    if (0 = person_count)
    then
        execute immediate
            'create table PERSON
             (
                 id number not null primary key,
                 role varchar2(10) not null,
                 login varchar(100) unique not null,
                 password varchar(100) not null,
                 surname varchar(100) not null,
                 name varchar(100) not null
             )';
    end if;
end;

declare
    sq_person_cnt number;
BEGIN
    SELECT count(1)
    into sq_person_cnt
    FROM user_sequences

    where sequence_name = 'SQ_FOR_PERSON';
    if (0 = sq_person_cnt)
    then
        EXECUTE IMMEDIATE 'create sequence sq_for_person
            start
        with 1
            increment by 1
            nomaxvalue ';
    end if;
END;

create or replace trigger tr_person
    before insert
    on PERSON
    for each row
begin
    select sq_for_person.NEXTVAL
    into :new.id
    from dual;
end;