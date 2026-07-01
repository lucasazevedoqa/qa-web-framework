# QA Web Automation Framework 🚀

Framework de automação de testes focado em aplicações Web, estruturado utilizando as melhores práticas de Engenharia de Software e a arquitetura **Page Objects (POM)**.

---

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java 21
* **Gerenciador de Dependências:** Maven
* **Framework de Testes:** JUnit 5 (Jupiter)
* **Automação Web:** Selenium WebDriver (v4.28.0)
* **Relatórios de Execução:** Allure Report & AspectJ Weaver
* **Captura de Evidências:** Apache Commons IO

---

🚀 Como Executar os Testes
1. Pré-requisitos
Ter o Java 21 instalado e configurado nas variáveis de ambiente (JAVA_HOME).

Ter o Maven instalado e configurado no PATH.

Navegador Google Chrome instalado.

2. Execução via Terminal
Para limpar os builds anteriores, compilar o framework e disparar todas as suítes de testes automatizados, execute:

Bash
mvn clean test
3. Execução Individual (IDE)
Graças ao alinhamento do Classpath do JUnit 5 Platform Launcher, você também pode abrir qualquer classe dentro do pacote tests/ e rodar os cenários clicando diretamente no botão Play (seta verde) ao lado do método.

📊 Relatórios com Allure
O framework gera automaticamente os arquivos de resultados do Allure na pasta allure-results após o término dos testes.

Gerar e abrir o relatório visualmente:
Se você tiver o Allure CLI instalado na sua máquina, execute:

Bash
allure serve allure-results
(Isso subirá um servidor local para visualizar o relatório detalhado com gráficos de pass/fail e os screenshots de evidência anexados automaticamente).

---

## 📂 Estrutura do Projeto

O projeto foi centralizado e otimizado dentro do diretório de testes (`src/test/java`) para unificar o ecossistema e simplificar a execução:

```text
src/
└── test/
    └── java/
        └── com/
            └── lucas/
                └── qa/
                    ├── base/        # Inicialização do WebDriver, Setup e Teardown
                    ├── config/      # Massa de dados fixas e configurações globais
                    ├── drivers/     # Gerenciamento de drivers locais
                    ├── extensions/  # Ouvintes do JUnit para capturar screenshots automáticos
                    ├── pages/       # Classes Page Objects com o mapeamento dos elementos da tela
                    ├── tests/       # Cenários de testes funcionais
                    └── utils/       # Classes utilitárias do sistema