declare club_count number;
begin
    select count(1)
    into club_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'CLUB';
    if (0 = club_count)
    then
        execute immediate
            'create table club
             (
                 name varchar(100) not null primary key
             )';
    end if;
end;