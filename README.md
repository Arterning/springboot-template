# File Structure
We are using Controller, Service, Repository layer in this starter. this way to ensure maintainable code based on behavior for each layer.
```
springboot.starter
 ├─common/                          * our common files
 │   ├──Constant                    * our files to maintain constant variables
 │   └──Tool                        * our files to maintain static/ reusable methods
 │
 ├──config/                         * our configuration files
 │   └──SwaggerConfig               * configuration file to configure swagger documentation
 │
 ├──dto
 │   ├──FailureResponse             * our JSON format that return by exception handler
 │   ├──SuccessResponse             * our JSON format that return is transaction/ process is success
 │   └──Response                    * our JSON standard JSON format
 │
 ├──exception                       * our custom exception files and exception handler
 │   └──HandlerException            * our runtime exception handler that return a nice JSON
 │
 ├──interceptor                     * our filter files
 │   ├──Interceptor                 * our main Interceptor
 │   └──LogInterceptor              * our pre and post handle that log activity in web service
 │
 ├──entity                          * our entity files that mapping to table in database
 │   └──Person                      * our object mapping table Person
 │
 ├──controller                      * our controller files that define endpoints/ route mapping
 │   └──PersonController            * our endpoint to handle Person module
 │
 ├──service                         * our service files to handle business logic
 │   └──PersonService               * our logic to handle Person module
 │
 └──repository                      * out repository files to handle query / command to database
     └──PersonRepository            * our query related with table Person

```


## Swagger2
访问地址
```bash
http://localhost:8080/api/swagger-ui.html
```