create index if not exists personal_doc_user_id_personal_id_doc_number_index
    on personal_doc (user_id, personal_id, doc_number);

create unique index if not exists personal_doc_user_id_uindex
    on personal_doc (user_id);