declare us_count number;
begin
    select count(1)
    into us_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'USER';
    if (0 = us_count)
    then
        execute immediate
            'create table "USER"
             (
                 id number not null primary key,
                 role varchar2(10) not null,
                 id_for_role number unique not null,
                 login varchar(100) unique not null,
                 password varchar(100) not null,
                 CONSTRAINT fk_person
                     FOREIGN KEY (id_for_role)
                         REFERENCES person(id)

             )';
    end if;
end;

declare
    sq_user_cnt number;
BEGIN
    SELECT count(1)
    into sq_user_cnt
    FROM user_sequences

    where sequence_name = 'SQ_FOR_USER';
    if (0 = sq_user_cnt)
    then
        EXECUTE IMMEDIATE 'create sequence sq_for_user
            start with 1
            increment by 1
            nomaxvalue';
    end if;
END;

create or replace trigger tr_user
    before insert
    on "USER"
    for each row
begin
    select sq_for_user.NEXTVAL
    into :new.id
    from dual;
end;