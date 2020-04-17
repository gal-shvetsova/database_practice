declare attribute_facility_kind_count number;
begin
    select count(1)
    into attribute_facility_kind_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'ATTRIBUTE_FACILITY_KIND';
    if (0 = attribute_facility_kind_count)
    then
        execute immediate
            'create table ATTRIBUTE_FACILITY_KIND
             (
                 name varchar(100) not null primary key ,
                 id_type varchar(100) not null,
                 CONSTRAINT fk_facility FOREIGN KEY (id_type) references FACILITY_KIND(name)
             )';
    end if;
end;
