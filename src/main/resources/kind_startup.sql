declare kind_count number;
begin
    select count(1)
    into kind_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'KIND';
    if (0 = kind_count)
    then
        execute immediate
            'create table kind
             (
                 name varchar(10) not null primary key,
             )';
    end if;
end;