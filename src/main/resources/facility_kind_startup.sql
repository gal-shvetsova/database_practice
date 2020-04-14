declare
    facility_kind_count number;
begin
    select count(1)
    into facility_kind_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'FACILITY_KIND';
    if (0 = facility_kind_count)
    then
        execute immediate
            'create table facility_kind
             (
                 name varchar(10) not null primary key
             )';
    end if;
end;