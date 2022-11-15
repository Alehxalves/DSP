# TRABALHO PRATICO II

1. Implemente a persistência das classes Filme e Ator usando o padrão DAO e a interface JPA para persistir (consultar e salvar/atualizar/deletar - CRUD) objetos das classes Filme e Ator com uma associação many-to-many, sem o uso de uma nova classe entre elas e de acordo com o diagrama UML a seguir:

2. A aplicação deve ter uma interface com o usuário com menus e submenus para realizar todas as operações de persistência implementadas.

3. A aplicação deve ter pelo menos uma consulta de cada um dos tipos de consulta a seguir:

   a) JPQL
   b) Named Query
   c) Criteria Query
   d) Native Query

4. A aplicação deve realizar a seguintes consultas:

   a) Obter ator e filme a partir de seus respectivos ids.
   b) Listar todos os títulos de filmes de um determinado ator.
   c) Listar os nomes de todos os atores de um determinado filme.
   d) Listar os títulos de filmes lançados em determinado ano.
   e) Listar os títulos de filmes cujo título contém determinada string.
   f) Listar os nomes de atores nascidos em determinado ano.
   g) Mostrar a quantidade total de filmes cadastrados.

5. Deixe DADOS REAIS de filmes e atores já cadastrados em seu banco de dados. Cadastre ao menos 3 filmes e pelo menos 2 atores para cada filme. Envie o Script de criação e povoamento do banco de dados, juntamente com seu trabalho. Dica: no postgresql use o utilitário pg_dump para fazer o backup do database. Exemplo: pg_dump -v -U postgres -W -d dbfilmes > dbfilmes.sql
