PGDMP     :                 	    z         	   dsPersist    10.22    10.22     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    24651 	   dsPersist    DATABASE     �   CREATE DATABASE "dsPersist" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE "dsPersist";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24848    aluno_turma_id_seq    SEQUENCE     �   CREATE SEQUENCE public.aluno_turma_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.aluno_turma_id_seq;
       public       postgres    false    3            �            1259    24850    aluno_turma    TABLE     �   CREATE TABLE public.aluno_turma (
    id integer DEFAULT nextval('public.aluno_turma_id_seq'::regclass) NOT NULL,
    aluno_id integer,
    turma_id integer,
    nota_final numeric(4,2),
    qtd_faltas integer
);
    DROP TABLE public.aluno_turma;
       public         postgres    false    200    3            �            1259    24733    alunos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.alunos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.alunos_id_seq;
       public       postgres    false    3            �            1259    24735    alunos    TABLE        CREATE TABLE public.alunos (
    id integer DEFAULT nextval('public.alunos_id_seq'::regclass) NOT NULL,
    nome character varying(50) NOT NULL,
    cpf character varying(14) NOT NULL,
    matricula integer NOT NULL,
    email character varying(50),
    telefone character varying(16)
);
    DROP TABLE public.alunos;
       public         postgres    false    196    3            �            1259    24803    turmas_id_seq1    SEQUENCE     �   CREATE SEQUENCE public.turmas_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.turmas_id_seq1;
       public       postgres    false    3            �            1259    24805    turmas    TABLE     �   CREATE TABLE public.turmas (
    id integer DEFAULT nextval('public.turmas_id_seq1'::regclass) NOT NULL,
    cod_turma integer,
    periodo character varying(12),
    disciplina character varying(255)
);
    DROP TABLE public.turmas;
       public         postgres    false    198    3                      0    24850    aluno_turma 
   TABLE DATA               U   COPY public.aluno_turma (id, aluno_id, turma_id, nota_final, qtd_faltas) FROM stdin;
    public       postgres    false    201   �                 0    24735    alunos 
   TABLE DATA               K   COPY public.alunos (id, nome, cpf, matricula, email, telefone) FROM stdin;
    public       postgres    false    197                    0    24805    turmas 
   TABLE DATA               D   COPY public.turmas (id, cod_turma, periodo, disciplina) FROM stdin;
    public       postgres    false    199   �                  0    0    aluno_turma_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.aluno_turma_id_seq', 2, true);
            public       postgres    false    200                       0    0    alunos_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.alunos_id_seq', 5, true);
            public       postgres    false    196                       0    0    turmas_id_seq1    SEQUENCE SET     <   SELECT pg_catalog.setval('public.turmas_id_seq1', 3, true);
            public       postgres    false    198            �
           2606    24855    aluno_turma aluno_turma_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.aluno_turma
    ADD CONSTRAINT aluno_turma_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.aluno_turma DROP CONSTRAINT aluno_turma_pkey;
       public         postgres    false    201            ~
           2606    24742    alunos alunos_cpf_key 
   CONSTRAINT     O   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_cpf_key UNIQUE (cpf);
 ?   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_cpf_key;
       public         postgres    false    197            �
           2606    24744    alunos alunos_matricula_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_matricula_key UNIQUE (matricula);
 E   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_matricula_key;
       public         postgres    false    197            �
           2606    24740    alunos alunos_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_pkey;
       public         postgres    false    197            �
           2606    24810    turmas turmas_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.turmas
    ADD CONSTRAINT turmas_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.turmas DROP CONSTRAINT turmas_pkey;
       public         postgres    false    199            �
           2606    24856    aluno_turma fk_aluno    FK CONSTRAINT     u   ALTER TABLE ONLY public.aluno_turma
    ADD CONSTRAINT fk_aluno FOREIGN KEY (aluno_id) REFERENCES public.alunos(id);
 >   ALTER TABLE ONLY public.aluno_turma DROP CONSTRAINT fk_aluno;
       public       postgres    false    2690    197    201            �
           2606    24861    aluno_turma fk_turma    FK CONSTRAINT     u   ALTER TABLE ONLY public.aluno_turma
    ADD CONSTRAINT fk_turma FOREIGN KEY (turma_id) REFERENCES public.turmas(id);
 >   ALTER TABLE ONLY public.aluno_turma DROP CONSTRAINT fk_turma;
       public       postgres    false    201    2692    199                  x�3�4BC= ����� ��         j   x�-�;
�0��SXj�ew�;=�7���"DE��F����)�~z����	�LQ�E]�*4�_t�#��fI���֪�C�Ձ�PԈ�	Ͳ��Ӹo���/�"��3�         f   x�3�44�4202�3�tI-N�+��)��M�+�WHIU�O+)O,JU(H,JTH-*�,.9�*/93�˘���խ4/%���-�(?�(17���Ë�b���� ��"�     