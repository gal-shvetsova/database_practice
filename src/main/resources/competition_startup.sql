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
                 id          number      not null primary key,
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

declare
    sq_for_competition_cnt number;
BEGIN
    SELECT count(1)
    into sq_for_competition_cnt
    FROM user_sequences

    where sequence_name = 'SQ_FOR_COMPETITION';
    if (0 = sq_for_competition_cnt)
    then
        EXECUTE IMMEDIATE 'create sequence SQ_FOR_COMPETITION
            start with 1
            increment by 1
            nomaxvalue';
    end if;
END;

create or replace trigger tr_competition
    before insert
    on competition
    for each row
begin
    select sq_for_competition.NEXTVAL
    into :new.id
    from dual;
end;