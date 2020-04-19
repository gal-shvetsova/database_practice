declare
    facility_count number;
begin
    select count(1)
    into facility_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'FACILITY';
    if (0 = facility_count)
    then
        execute immediate
            'create table facility
             (
                 name    varchar(100)          not null primary key,
                 address varchar2(100) unique not null,
                 kind    varchar(100)          not null,
                 CONSTRAINT fk_facility_kind
                     FOREIGN KEY (kind)
                         REFERENCES FACILITY_KIND(name)
             )';
    end if;
end;