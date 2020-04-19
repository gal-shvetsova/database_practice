declare sport_count number;
begin
    select count(1)
    into sport_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'SPORT';
    if (0 = sport_count)
    then
        execute immediate
            'create table sport
             (
                 name varchar(100) not null primary key
             )';
    end if;
end;