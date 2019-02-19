-- Table: public.resume

-- DROP TABLE public.resume;

CREATE TABLE public.resume
(
    uuid CHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT NOT NULL
);
CREATE INDEX resume_uuid_index ON public.resume (uuid);

-- Table: public.contact

-- DROP TABLE public.contact;

CREATE TABLE public.contact
(
    id SERIAL PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL,
    type TEXT NOT NULL,
    value TEXT NOT NULL,
    CONSTRAINT contact_resume_uuid_fk FOREIGN KEY (resume_uuid) REFERENCES resume (uuid) ON DELETE CASCADE
);
CREATE UNIQUE INDEX contact_uuid_type_index ON public.contact (resume_uuid, type);
