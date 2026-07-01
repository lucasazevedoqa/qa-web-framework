# SauceDemo Automation Framework

Este projeto consiste em um framework de automação de testes End-to-End (E2E) desenvolvido para a plataforma de e-commerce [SauceDemo](https://www.saucedemo.com/). A arquitetura foi projetada com foco em alta manutenibilidade, legibilidade, robustez contra intermitências e total isolamento de responsabilidades utilizando o padrão Page Objects.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21 (JDK 21):** Versão da linguagem base adotada para a construção do projeto, aproveitando os recursos modernos de performance e segurança.
* **Selenium WebDriver (v4.18.1):** Biblioteca core utilizada para a manipulação nativa e simulação de ações no navegador web.
* **JUnit 5:** Framework de testes encarregado de orquestrar a execução dos cenários, ciclo de vida (`@BeforeEach`/`@AfterEach`) e asserções.
* **Apache Commons IO:** Utilitário para manipulação de arquivos físicos no sistema operacional durante a cópia e salvamento de evidências.

---
## ⚙️ O que cada componente faz?

### 1. Camada de Infraestrutura (`base` & `drivers`)
* **`DriverFactory.java`:** Centraliza a criação do navegador através do padrão *Factory Method*. Isola a definição dos parâmetros do `ChromeOptions` (como desabilitar notificações e mitigar restrições de CORS) garantindo que o navegador sempre nasça limpo e padronizado.
* **`BaseTest.java`:** Gerencia as anotações de ciclo de vida do JUnit 5. Garante que cada método de teste abra uma janela dedicada do navegador e que, ao término de cada cenário, o processo do WebDriver seja encerrado corretamente no sistema operacional, acionando a captura de imagem.

### 2. Camada de Configurações (`config`)
* **`Config.java`:** Armazena parâmetros globais do ambiente, como a URL principal e o tempo de timeout padrão para as esperas explícitas. Blindada com construtor privado para impedir instanciações indevidas na memória.
* **`DadosDeTeste.java`:** Reúne usuários, senhas e literais de erro do negócio. Evita o espalhamento de strings soltas (*hardcoded*) no meio do código dos testes.

### 3. Camada de Telas (`pages` - Page Objects)
> 💡 *Todas as classes nesta camada utilizam atributos `private final` para proteger os localizadores (`By`) e herdam a inteligência de esperas explícitas (`WebDriverWait`), eliminando completamente o uso de pausas estáticas e instáveis como `Thread.sleep`.*

* **`LoginPage`:** Realiza a digitação das credenciais e captura alertas visuais de erro de login.
* **`ProdutosPage`:** Mapeia botões de itens e gerencia o clique para o direcionamento ao carrinho.
* **`CarrinhoPage`:** Extrai o texto dos produtos inseridos para validação e inicia a jornada de checkout.
* **`CheckoutInformationPage` & `CheckoutOverviewPage`:** Injetam os dados cadastrais e processam os botões de avanço utilizando chamadas nativas de **JavaScript Executor**, contornando problemas físicos de foco ou interceptação de clique que travam o Selenium convencional.
* **`CheckoutCompletePage`:** Captura os cabeçalhos de sucesso de conclusão do pedido para auditoria e checagem final.

### 4. Camada de Utilitários (`utils`)
* **`ScreenshotUtils.java`:** Converte o driver para a interface `TakesScreenshot`, extrai o arquivo temporário gerado pelo navegador e o salva na pasta local `/evidencias` ordenado de forma cronológica por timestamp (Data/Hora), nome do teste e status do cenário.

### 5. Orquestração de Testes (`tests`)
* **Camada limpa e puramente descritiva:** Consome os métodos expostos pelas páginas e faz uso das asserções nativas do JUnit (`assertEquals`, `assertTrue`) para garantir que o comportamento exibido pelo browser seja exatamente igual ao esperado pelo negócio.

---

## 🚀 Como Executar o Projeto

Atualmente, o projeto está estruturado para execução direta através do ecossistema da sua IDE de preferência (como IntelliJ IDEA ou Eclipse):

1. Certifique-se de que o **JDK 21** esteja configurado e selecionado nas preferências de compilação da sua IDE.
2. Navegue até o pacote `com.lucas.qa.tests`.
3. Clique com o botão direito sobre a classe de teste desejada (ou no pacote completo) e selecione a opção **Run 'Tests'** para disparar o motor do JUnit 5.
4. As evidências visuais em formato `.png` serão geradas automaticamente na pasta `evidencias/` localizada na raiz do projeto ao final de cada execução.
   
--- 

## 📐 Arquitetura do Projeto

O framework segrega a massa de dados, a infraestrutura e as interações das páginas de forma organizada dentro do diretório `src/test/java`:


O framework segrega a massa de dados, a infraestrutura e as interações das páginas de forma organizada dentro do diretório `src/test/java`:

```text
src/test/java/com/lucas/qa/
│
├── base/
│   └── BaseTest.java               # Configuração do ciclo de vida dos testes (Setup e TearDown)
│
├── config/
│   ├── Config.java                 # Constantes de infraestrutura e timeouts globais
│   └── DadosDeTeste.java           # Massa de dados fixas e mensagens do sistema
│
├── drivers/
│   └── DriverFactory.java          # Fábrica isolada para inicialização do WebDriver
│
├── pages/
│   ├── LoginPage.java              # Ações e mapeamento da tela de autenticação
│   ├── ProdutosPage.java           # Ações e mapeamento da listagem de produtos e carrinho
│   ├── CarrinhoPage.java           # Ações e mapeamento do carrinho de compras
│   ├── CheckoutInformationPage.java# Formulário de entrada dos dados do comprador
│   ├── CheckoutOverviewPage.java   # Revisão e valores totais do pedido consolidado
│   └── CheckoutCompletePage.java   # Tela final de confirmação de sucesso da compra
│
├── tests/
│   ├── AdicionarProdutoAoCarrinho.java # Fluxo E2E completo de compra bem-sucedida
│   ├── LoginComSucessoTest.java        # Validação do caminho feliz do login
│   └── LoginComFalhaTest.java          # Validação de cenário negativo de login com erro
│
└── utils/
    └── ScreenshotUtils.java        # Utilitário para captura de tela e geração de imagens


