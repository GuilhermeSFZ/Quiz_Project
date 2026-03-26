# 🧠 Quiz Interativo - Sistema Desktop

<p align="center">
  <img src="src/main/resources/background.png" alt="Logo do jogo" width="300"/>
</p>

Este projeto consiste em uma aplicação desktop de quiz interativo, onde usuários podem se cadastrar, responder perguntas e competir em um ranking de pontuações.

Usuários com perfil de administrador possuem funcionalidades adicionais, como o gerenciamento do sistema, podendo cadastrar, editar e remover perguntas, além de acompanhar o desempenho geral dos participantes.

## 📊 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de:

Praticar o desenvolvimento de interfaces gráficas
Implementar um sistema de autenticação de usuários
Aplicar conceitos de persistência de dados

## 📂 Estrutura do Projeto

O projeto segue o padrão de arquitetura **MVC (Model-View-Controller)** para separar a lógica de negócio da interface gráfica e do acesso aos dados.

```text
Quiz_Project/
├── 📦 com.ufmt.computacao.quiz.dao     # Camada de Acesso a Dados (Hibernate)
│   ├── 📄 QuestaoDAO.java              
│   ├── 📄 RankingDAO.java              
│   └── 📄 UsuarioDAO.java              
├── 📦 com.ufmt.computacao.quiz.gui     # Interface Gráfica (View/Controller)
│   ├── 📄 CadastroApp.java             
│   ├── 📄 CadastrarQuestaoApp.java     
│   ├── 📄 EditarQuestaoApp.java        
│   ├── 📄 EscolherDificuldadeApp.java  
│   ├── 📄 PainelAdminApp.java          
│   ├── 📄 QuizApp.java                 
│   ├── 📄 RankingApp.java              
│   ├── 📄 UsuarioApp.java              
│   └── 📄 VisualizarQuestoesApp.java   
├── 📦 com.ufmt.computacao.quiz.model    # Entidades (Classes de Modelo)
│   ├── 📄 Questao.java                 
│   ├── 📄 Ranking.java                 
│   └── 📄 Usuario.java                 
└── 📂 Other Sources                    # Configurações e Recursos
    ├── 📄 hibernate.cfg.xml            # Configuração do Banco de Dados
    └── 🖼️ background.png               # Assets visuais

---

## 📌 Funcionalidades

### 👤 Área do Usuário
* **Sistema de Cadastro e Login:** Fluxo de autenticação para novos jogadores, com suporte para diferenciação de níveis de acesso (Usuário comum vs. Administrador).
* **Seleção de Dificuldade:** Opção para filtrar o desafio do Quiz entre os níveis **Fácil**, **Médio** e **Difícil** antes de iniciar a partida.
* **Ranking de Pontuação:** Visualização em tempo real das melhores pontuações registradas, permitindo competição entre os usuários.

### 🛠️ Painel do Administrador
O sistema conta com um módulo de gerenciamento restrito para controle total do conteúdo:
* **Gestão de Questões (CRUD):** * **Cadastrar:** Interface dedicada para inserir enunciado, quatro alternativas (A, B, C, D) e definir a resposta correta.
    * **Visualizar:** Tabela de listagem de todas as questões cadastradas no banco de dados.
    * **Editar/Excluir:** Ferramentas para manutenção e correção do banco de perguntas.
* **Gerenciamento de Ranking:** Acesso administrativo para monitorar o desempenho global dos usuários.

### ⚙️ Características Técnicas
* **Persistência com Hibernate/JPA:** Mapeamento Objeto-Relacional (ORM) para comunicação eficiente com o banco de dados MySQL.
* **Interface Gráfica (GUI):** Desenvolvida com foco em usabilidade, utilizando janelas modais para cadastros e transições de tela.
* **Logs de Transação:** Monitoramento em console de todas as operações de banco de dados (Select, Insert, Update) via logs do Hibernate.

## 👤 Autor

Guilherme da Silva Ferraz.
