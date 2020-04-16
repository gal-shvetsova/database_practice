declare
    competition_count number;
begin
    select count(1)
    into competition_count
    from dba_tables
    where owner = 'SHVETSOVA'
      and table_name = 'COMPETITION';
    if (0 = competition_count)
    then
        execute immediate
            'create table competition
             (
                 id          varchar(100)      not null primary key,
                 sport       varchar(10) not null,
                 facility    varchar(10) not null,
                 start_date  date        not null,
                 finish_date date        not null,
                CONSTRAINT fk_competition_sport
                     FOREIGN KEY (sport)
                     REFERENCES SPORT(name),
                 CONSTRAINT fk_competition_facility
                     FOREIGN KEY (facility)
                         REFERENCES facility(NAME)
             )';
    end if;
end;
