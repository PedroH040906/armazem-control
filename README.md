# armazem-control
# 📦 Sistema de Controle de Estoque e Armazéns

Este projeto tem como objetivo a construção de uma API RESTful para gerenciamento de estoque em diferentes armazéns. É possível registrar produtos, criar e atualizar saldos, transferir saldos entre armazéns e manter um histórico detalhado de todas as movimentações.

---

## 🔧 Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

---

## ⚙️ Funcionalidades

- 📦 **Cadastro de Produtos**  
  Criação e listagem de produtos com suas informações básicas.

- 🏬 **Gestão de Armazéns**  
  Gerenciamento dos locais onde os produtos estão armazenados.

- 💰 **Controle de Saldos**  
  Permite registrar a quantidade de produtos disponíveis em cada armazém.

- 🔄 **Transferência de Saldos**  
  Transfere saldo de um produto entre dois armazéns, com verificação de quantidade.

- 🕘 **Histórico de Movimentações**  
  Armazena todos os eventos do sistema, como criação, adição e transferência de saldos.

---

## 🗃️ Modelo de Dados

As principais entidades do sistema são:

- `Produto`
- `Armazem`
- `Saldo`
- `Historico`

Cada `Historico` está ligado a um tipo de movimentação (`CRIACAO`, `ADICAO`, `TRANSFERENCIA`) e registra detalhes como produto, quantidade, armazém de origem e destino.

---
