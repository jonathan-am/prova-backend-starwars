**Application consumes data via REST request.**

**TODO** *Raise application unit test coverage.*

**Indications for using the features:**

* SEARCH FOR A PLANET BY ID, IF YOU DON'T FIND IT IN THE BANK, CONSULT IT IN SWAPI.DEV AND STORE IT IN THE DATABASE:

`curl --request GET \
  --url http://localhost:8080/planet/v2/1`
  
* SEARCH FOR A PLANET BY NAME, IF YOU DON'T FIND IT IN THE BANK, A 404 WILL BE RETURNED, INFORMING YOU TO USE ID AS SEARCH:

`curl --request GET\
  --url http://localhost:8080/planet/v1/Tatooine`
  
  
* SEARCH FOR ALL PLANETS SAVED IN THE DATABASE:

`curl --request GET\
  --url http://localhost:8080/planet`


* DELETE A PLANET BY IDENTIFIER:

`curl --request DELETE\
  --url http://localhost:8080/planet/1`


* CREATES A PLANET, USING THE IDENTIFIER AS A KEY, IT CANNOT BE NULL, 0 OR EXISTING IN THE DATABASE:

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
