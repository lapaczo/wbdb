# Initial setup
* Application uses MariaDB as its master database.
* Initially it tries to connect to following destination:
    * url: `"jdbc:mariadb://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_DB:wbdb}"`
* with following credentials:
    * username: `${DB_USERNAME:wbdb}`
    * password: `${DB_PASSWORD:wbdb}`
* url and credentials are configurable by environment variables.
* All persisted connection details creates data sources.

# Running application
* Application supports only new connections to MariaDB databases.
* REST API documentation is available on following address:
    * http://localhost:8080/swagger-ui.html#/

#
![](https://pics.ballmemes.com/your-code-cant-fail-unit-tests-openim-if-you-dont-14499557.png)
