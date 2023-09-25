# WeFin
### README para o projeto WeFin - Gestão de Empréstimos da Empresa X

---

#### 📌 Visão Geral

O projeto **WeFin - Gestão de Empréstimos da Empresa X** é uma aplicação Java Spring Boot desenvolvida para gerenciar empréstimos financeiros. Ele oferece uma API RESTful para criar, listar, atualizar e deletar empréstimos, bem como gerenciar pessoas associadas a esses empréstimos.

---

#### 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- JPA (Java Persistence API)
- Lombok
- PostgreSQL

---

#### 📂 Estrutura do Projeto
GestaoEmprestimosEmpresaXApplication.java - Classe principal da aplicação.

Configurações
- SecurityConfig.java - Configurações de segurança.
- WebMvcConfig.java - Configurações do MVC.

Controladores
- EmprestimoController.java - Controlador para empréstimos.
- PessoaController.java - Controlador para pessoas.

DTOs
- EmprestimoDTO.java - DTO para empréstimos.
- PessoaDTO.java - DTO para pessoas.

Modelos
- Emprestimo.java - Modelo para empréstimos.
- Pessoa.java - Modelo para pessoas.

Repositórios
- EmprestimoRepository.java - Repositório para empréstimos.
- PessoaRepository.java - Repositório para pessoas.

Serviços
- EmprestimoService.java - Serviço para empréstimos.
- PessoaService.java - Serviço para pessoas.

Factory
- IdentificadorFactory.java - Fábrica para criar estratégias de validação de identificadores.

Validações
- APValidator.java - Validador para AP.
- CNPJValidator.java - Validador para CNPJ.
- CPFValidator.java - Validador para CPF.
- EUValidator.java - Validador para EU.
- IdentificadorStrategy.java - Estratégia para identificadores.
- ValidaCNPJ.java - Classe para validar CNPJ.
- ValidaCPF.java - Classe para validar CPF.

---

#### 🚀 Como Executar o Projeto

1. Clone o repositório para sua máquina local.
2. Navegue até o diretório do projeto e execute o comando `mvn clean install` para instalar as dependências.
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.
4. Acesse `http://localhost:8080/api/emprestimos` para interagir com a API.

---

#### 📝 Licença

Este projeto está sob a licença MIT.
