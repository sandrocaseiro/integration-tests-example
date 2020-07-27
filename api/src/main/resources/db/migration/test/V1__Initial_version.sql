CREATE TABLE public.usuario (
    id integer primary key auto_increment,
    nome varchar(50) NOT NULL,
    email varchar(150) NOT NULL,
    CONSTRAINT usuario_email_un UNIQUE (email)
);
