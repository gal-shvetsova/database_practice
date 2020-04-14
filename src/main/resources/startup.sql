begin
        execute immediate
            'create table "USER"
                (
                    id number not null primary key,
                    role varchar2(10) not null,
                    id_for_role number unique not null
                )';
end;
