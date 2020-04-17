declare
    attribute_facility_count number;
begin
    select count(1)
    into attribute_facility_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'ATTRIBUTE_FACILITY';
    if (0 = attribute_facility_count)
    then
        execute immediate
            'create table ATTRIBUTE_FACILITY
             (
                 name_attribute varchar(100) not null,
                 id_facility varchar(100) not null,
                 value number not null,
                 constraint ATTRIBUTE_FACILITY_PK primary key (name_attribute, id_facility),
                 constraint fk_attr foreign key (name_attribute) references ATTRIBUTE_FACILITY_KIND(id),
                 constraint fk_facility_attr foreign key (id_facility) references FACILITY(name)
             )';
    end if;
end;









