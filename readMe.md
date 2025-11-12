Excercise:

2 services

(source of truth , book of record)
service A: apis for "get git user" and "add git user", 
    git user - entity, jpa, crud operation, controller get / post operations

(caching)
service B: 
- api - accept username: 
- - call service A to fetch that user
- - if service A is giving back user then -> return that in response back
- - if service A does not have that user
- -- call fetch user from GitHub, -> use post api of service A to persist that
         -> and return that in response back

## Feign client -> declarative approch of consuming apis
interfaces , # june25-spring-httpclients


## Spring AOP: Aspect oriented programming 
cutting secondery logics ( logging )
cross cutting concerns 
OOPs
Func programming