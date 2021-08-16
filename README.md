# bank-transactions

## What this service do?

> Service responsible for make transactions

#### Architecture design

TBD

#### Dependency

> - [Maven](https://maven.apache.org/)
> - [New Relic](https://newrelic.com/)

#### Environment Requirements

- `JDK OpenJ9 11`
- `make`
- `docker`
- `docker-compose` *version above 1.22.0*

#### Como rodar localmente

- Utilizando linha de comando

```sh
git clone git@github.com:michelalves/bank-transactions.git
make  bank-transactions/development-environment/Makefile.mk start
```

- Utilizando IDE
    1. Devemos inicializar as dependências do serviço pela IDE usando o Makefile.mk do projeto ou por shell na **raiz**
       do projeto com o comando: `make development-environment/Makefile.mk start-environment`
    3. Inicializar o serviço pela classe `Application.java`.


- Utilizando Docker

```sh
`docker build -t`
`docker run -d -p 8080:8080 transactions`
```

- Para conectar no banco de dados do container:

- user: `bank-transactions`
- password: `bank-transactions`
- db: `bank-transactions`
- host: `localhost:5432`
- url: `jdbc:postgresql://localhost:5432/banktransactions`

#### Api Documentation

http://localhost:8080/swagger-ui.html

#### Examples

- Create account

```
curl --location --request POST 'http://localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data-raw '{ "document": "04725128970" }'
```

- Get account by ID

```
curl 'http://localhost:8080/accounts/1'
```

- Register transaction

```
curl POST 'http://localhost:8080/transactions' \
   --header 'Content-Type: application/json' \
   --data-raw '{
       "account_id": 1,
       "operation_type_id": 1,
       "amount": 123.32
   }'
```
