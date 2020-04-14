declare
    organizer_count number;
begin
    select count(1)
    into organizer_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'ORGANIZER';
    if (0 = organizer_count)
    then
        execute immediate
            'create table organizer
             (
                 name varchar(10) not null primary key
             )';
    end if;
end;