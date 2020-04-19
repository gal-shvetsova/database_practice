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
                 id varchar2(100) not null primary key,
                 role varchar2(100) not null,
                 login varchar(100) unique not null,
                 password varchar(100) not null,
                 surname varchar(100) not null,
                 name varchar(100) not null
             )';
    end if;
end;



