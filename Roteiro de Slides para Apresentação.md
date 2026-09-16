## Roteiro de Slides para Apresentação

## Slide 1: Capa

* Título: Blueprint de Integração Corporativa Universal
* Subtítulo: Arquitetura Híbrida Cloud-Native (Spring Batch + Apache Camel)
* Apresentador: [Seu Nome / Time de Arquitetura]

------------------------------
## Slide 2: O Desafio do Alinhamento Tecnológico

* Escopo do Databricks: Plataforma excelente para Engenharia de Dados e IA; usá-la como orquestrador operacional de APIs (Salesforce Bulk v2) gera desperdício de compute analítico.
* Limitações do Mulesoft: Licenciamento caro baseado em vCores, monitoramento isolado das ferramentas da empresa e dependência de mão de obra de mercado altamente especializada.
* Governança Fragmentada: Lógicas de integração espalhadas dificultam a aplicação de padrões unificados de segurança.

------------------------------
## Slide 3: A Solução Híbrida (Batch & Streaming)

* Conceito Core: Microsserviços efêmeros rodando em containers e reutilizando a mesma stack Java.
* Modo Batch (Agendado): Extração em blocos (chunks) do Delta Lake e entrega via Salesforce Bulk v2 com controle de estado no PostgreSQL.
* Modo Streaming (Event-Driven): Consumo reativo em tempo real via Apache Camel integrado ao Kafka ou Salesforce Platform Events.

------------------------------
## Slide 4: O Funcionamento Dinâmico da Infraestrutura

* Agendamento com Kubernetes CronJobs: Disparos sob demanda, sem servidores ociosos ligados 24/7 (FinOps nativo).
* Ciclo de Vida com Spring Cloud Task: Inicializa o pod, executa a carga e garante que o recurso seja desalocado imediatamente ao término.
* Garantia de Estado com PostgreSQL: Histórico de metadados e garantia de restartability exata em caso de falha de rede.

------------------------------
## Slide 5: Validação de Mercado (Missão Crítica Global)

* UPS: Processa dezenas de bilhões de mensagens diárias usando [Apache Camel](https://camel.apache.org/) na malha logística.
* CERN: Orquestra 190 milhões de mensagens diárias no controle do Grande Colisor de Hádrons.
* IndiGo Airlines & Temenos: Conectam centenas de aplicações e bancos com estabilidade e código aberto.

------------------------------
## Slide 6: Observabilidade e DevSecOps

* Dynatrace: Métricas e logs centralizados de ponta a ponto (do Ponto A ao Ponto B), integrados aos dashboards corporativos de TI.
* Portões de Qualidade na CI/CD:
* [SonarQube](https://docs.sonarqube.org/): Qualidade de código e testes.
   * [Fortify](https://www.opentext.com/products/fortify-static-code-analyzer): Varredura estática de segurança (SAST).
   * [Mend](https://www.mend.io/): Gestão de dependências e CVEs (SCA).

------------------------------
## Slide 7: Matriz de Decisão (Quando Usar)

* Quando Usar: Cargas em lote pesadas para SaaS, fluxos com necessidade de retry/restart transacional, cenários event-driven com Kafka/Platform Events e foco em FinOps.
* Quando Não Usar: Consultas puramente analíticas ou machine learning massivo (manter no Databricks).

------------------------------
## Slide 8: O Case Real de Descomissionamento

* O Cenário Anterior (Mulesoft): Gargalos de memória, custos de licença elevados e isolamento operacional.
* O Resultado Atual (Spring + Camel): Redução total de custos de licenciamento, adoção de padrões internos de Java, pods efêmeros em Kubernetes e rastreio completo no Dynatrace.

------------------------------
## Slide 9: Conclusão

* Resumo: Uma fundação de integração segura, barata, mantida pela engenharia interna e adaptável a qualquer evolução do ecossistema de dados da companhia.