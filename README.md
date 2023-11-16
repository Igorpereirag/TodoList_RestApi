*TodoList ApiRestfull Spring Boot*

Este é um aplicativo de lista de tarefas simples desenvolvido em 
Java Spring Boot. Ele permite que os usuários criem, atualizem,
 removam e visualizem suas tarefas pendentes.

Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:
- Java JDK 8 ou superior
- Maven
- IDE (Eclipse, IntelliJ, ou outra de sua escolha)

Configuração do Banco de Dados:
O aplicativo usa um banco de dados embutido H2 por padrão. Se necessário,
 você pode modificar as configurações do banco de dados no arquivo "application.properties"

properties:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=root
spring.datasource.password=root
spring.h2.console.enabled=true

Configuração da Aplicação:

Clone o repositório:
git clone https://github.com/Igorpereirag/TodoList_RestApi.git

Abra o projeto em sua IDE favorita.

Execute a aplicação:

- mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.

Uso:

Acesse a interface da aplicação no navegador ou use uma ferramenta 
como o Postman para interagir com a API RESTful.

Endpoint para listar todas as tarefas: GET http://localhost:8080/task
Endpoint para criar uma nova tarefa: POST http://localhost:8080/task
Endpoint para atualizar uma tarefa existente: PUT http://localhost:8080/task/{id}
Endpoint para excluir uma tarefa: DELETE http://localhost:8080/task/{id}


possui uma interface html na pasta "view" feita com bootstrap e javascript contudo, necessita de alguns aprimoramentos e correções de bugs


Contribuição
Sinta-se à vontade para contribuir para o desenvolvimento deste projeto. Basta seguir as práticas de contribuição padrão e enviar uma solicitação de pull.

Licença
Este projeto é licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.

STATUS: Em aprimoramento