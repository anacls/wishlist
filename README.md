### Wishlist
#### Descrição:

Esse projeto é uma API que representa um sistema de 'Lista de Favoritos'

### Índice
* [Pré-Requisitos](#pré-requisitos)
* [Para começar](#para-começar)
* [Compilando e Rodando o projeto](#compilando-e-rodando-o-projeto)
* [API](#api)
   * [User/Customer](#users)
   * [Wishlist](#wishlist)


### Pré-Requisitos:
* [Java 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* Instalação do maven:

  `sudo apt update`

  `sudo apt install maven`

* Instalação do mysql:

  `sudo apt update`

  `sudo apt install mysql-server`

### Para começar:
* Clone este repositório:

  `git clone git@github.com:anacls/wishlist.git`

* Crie um banco de dados no mysql com o nome `labs_test`:

  `sudo mysql`

  `CREATE DATABASE labs_test;`

* Substitua as credenciais para login no arquivo persistence.xml:

  * Nas linhas [19](https://github.com/anacls/wishlist/blob/master/src/main/resources/META-INF/persistence.xml#L19) e [43](https://github.com/anacls/wishlist/blob/master/src/main/resources/META-INF/persistence.xml#L43) substitua `root` com o username que você está utilizando para acessar o banco.

    ***OBS:** Se você não definiu nenhum username diferente na instalação provavelmente ele deve ser `root` e não será necessária a substituição*

 * Caso tenha definido uma senha, descomente as linhas [20 e 21](https://github.com/anacls/wishlist/blob/master/src/main/resources/META-INF/persistence.xml#L20-L21) e [44 e 45](https://github.com/anacls/wishlist/blob/master/src/main/resources/META-INF/persistence.xml#L44-L45) e adicione a sua senha entre as aspas `("")` no campo `value`.

### Compilando e rodando o projeto
* Para compilar o projeto vá até a pasta onde se encontra o arquivo `pom.xml` e execute o comando:

   `mvn clean install`
* Se o resultado do build for `BUILD SUCCESS`, rode o projeto usando:

   `mvn exec:java`

   * Quando o projeto estiver rodando um log parecido com esse vai aparecer no terminal:

     ```
     [Thread-1] INFO org.eclipse.jetty.server.AbstractConnector - Started ServerConnector@2356afc7{HTTP/1.1,[http/1.1]}{0.0.0.0:4567}
     [Thread-1] INFO org.eclipse.jetty.server.Server - Started @2384ms
     ```

     Esse log indicará em que porta o servidor estará rodando, nesse caso, ele está rodando na porta `4567` e é para essa porta que devemos direcionar nossas requisições.

     `localhost:4567/{path_da_api_aqui}`

### API

#### Users
* Adicionar um usuário

   `POST: api/users`

    **Query Params:**
    * name: nome_do_usuario;
    * email: email_do_usuario;

    **Retorno:**
    * Se o usuário for adicionado:

      `status: 201`

      `Usuário criado com sucesso`

    * Se o usuário não for adicionado:

      `status: 400`

      `Não foi possível criar um novo usuário, verifique se esse usuário já existe`

* Ver um usuário

   `GET: api/users/{:id}`

    **Params:**
    * id: id_do_usuario;


    **Retorno:**
    * Se o usuário existir:

      `status: 200`

      ```
      Usuário
      Nome: nome_do_usuario, Email: email_do_usuario, Role: role_do_usuario
      ```

    * Se o usuário não existir:

      `status: 404`

      `Usuário não existente na base de dados`

* Ver todos os usuários

  `GET /api/users`

  **Retorno:**

   `status:200`
   ```
   Usuários
   Nome: nome_do_usuario, Email: email_do_usuario, Role: role_do_usuario

   Nome: nome_do_usuario, Email: email_do_usuario, Role: role_do_usuario
   ```

* Excluir um usuário

   `DELETE: api/users/{:id}`

    **Params:**
    * id: id_do_usuario;


    **Retorno:**
    * Se o usuário for excluído:

      `status: 200`

      `Excluido com sucesso`

    * Se o usuário não for excluído:

      `status: 400`

      `Não foi possível excluir o usuário`

* Atualizar um usuário

   `PUT: api/users/{:id}`

    **Params:**
    * id: id_do_usuario;

    **Query Params:**
    * name: nome_do_usuario;
    * email: email_do_usuario;

    **Retorno:**
    * Se o usuário for atualizado:

      `status: 200`

      `Usuário atualizado com sucesso`

    * Se o usuário não for atualizado:

      `status: 400`

      `Não foi possível atualizar usuário, usuário não existe`

#### Wishlist
* Adicionar na wishlist

  `POST: api/wishlist`

  **Query Params:**
  * user_id: id_do_cliente;
  * product_id: id_do_produto;

  **Retorno:**
  * Se o produto for adicionado à lista:
    `status:201`

    `Produto adicionado aos favoritos`
  * Se o produto não for adicionado à lista:
    `status:400`

    `Não foi possível adicionar produto aos favoritos`

* Ver item da lista de favoritos

   `GET: api/wishlist/{:id}`

    **Params:**
    * id: id_do_item;


    **Retorno:**
    * Se o item existir:

      `status: 200`

      ```
      Item
      id: id_do_item, Cliente: id_do_cliente
      ```

    * Se o item não existir:

      `status: 404`

      `Item não existente na lista de favoritos`

* Excluir um item

   `DELETE: api/wishlist/{:id}`

    **Params:**
    * id: id_do_item;


    **Retorno:**
    * Se o item for excluído:

      `status: 200`

      `Item excluido com sucesso`

    * Se o usuário não for excluído:

      `status: 400`

      `Não foi possível excluir item`

* Mostrar lista de favoritos de um cliente

   `GET: api/wishlist/user/{:id}`

    **Params:**
    * id: id_do_usuário;


    **Retorno:**

    `status: 200`

    ```
    Favoritos
    Produto: id_do_produto
    Produto: id_do_produto
    ```
