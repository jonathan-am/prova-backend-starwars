**Aplicação consome dados via requisição REST.**

**TODO** *Levantar cobertura de testes unitarios da aplicação.

**Enpoints para uso das funcionalidades:**

* BUSCA UM PLANETA PELO ID, CASO NÃO ENCONTRE NO BANCO, CONSULTA NO SWAPI.DEV E ARMAZENA NO BANCO DE DADOS:

`curl --request GET \
  --url http://localhost:8080/planet/v2/1`
  
* BUSCA UM PLANETA PELO NOME, CASO NÃO ENCONTRE NO BANCO, RETORNA UM 404, INFORMANDO PARA USAR ID COMO BUSCA:

`curl --request GET \
  --url http://localhost:8080/planet/v1/Tatooine`
  
  
* BUSCA POR TODOS OS PLANETAS SALVOS NO BANCO DE DADOS:

`curl --request GET \
  --url http://localhost:8080/planet`


* DELETA UM PLANETA PELO IDENTIFICADOR:

`curl --request DELETE \
  --url http://localhost:8080/planet/1`


* CRIA UM PLANETA, USANDO O IDENTIFICADOR COMO CHAVE, NÃO PODENDO SER NULL, 0 OU EXISTENTE NO BANCO DE DADOS:

`curl --request POST \
  --url http://localhost:8080/planet/ \
  --header 'Content-Type: application/json' \
  --data '{
	  "identifier": 2,
    "name": "Alderaan",
    "climate": "temperate",
    "terrain": "grasslands, mountains",
    "films": [
      {
        "titulo": "A New Hope",
        "naves": [
          {
            "name": "CR90 corvette",
            "model": "CR90 corvette",
            "manufacturer": "Corellian Engineering Corporation",
            "cost_in_credits": "3500000",
            "length": "150",
            "max_atmosphering_speed": "950",
            "crew": "30-165",
            "passengers": "600",
            "cargo_capacity": "3000000",
            "consumables": "1 year",
            "hyperdrive_rating": "2.0",
            "starship_class": "corvette",
            "pilots": [],
            "films": [
              "http://swapi.dev/api/films/1/",
              "http://swapi.dev/api/films/3/",
              "http://swapi.dev/api/films/6/"
            ],
            "created": "2014-12-10T14:20:33.369000Z",
            "edited": "2014-12-20T21:23:49.867000Z",
            "mglt": "60"
          }
        ] 
     }'`
