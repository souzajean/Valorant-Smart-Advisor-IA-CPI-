# 🎮 Valorant Smart Advisor – IA + SAP CPI

> **Solução de recomendação inteligente para Valorant construída no SAP Integration Suite (CPI), combinando orquestração de APIs, Groovy Scripts e IA Generativa.**

<p align="center">
  <img src="https://img.shields.io/badge/SAP-CPI-blue?style=for-the-badge&logo=sap" alt="SAP CPI">
  <img src="https://img.shields.io/badge/Groovy-4298B8?style=for-the-badge&logo=apachegroovy&logoColor=white" alt="Groovy">
  <img src="https://img.shields.io/badge/IA-Generativa-7B3FF2?style=for-the-badge" alt="IA Generativa">
  <img src="https://img.shields.io/badge/REST-API-00ADEF?style=for-the-badge&logo=rest&logoColor=white" alt="REST API">
  <img src="https://img.shields.io/badge/Postman-Testing-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman">
</p>

<p align="center">
  <img src="imagens/capa-linkedin.png" alt="Fluxo Principal" width="100%">
</p>

---

## 📋 Índice

<details open>
<summary><strong>Clique para expandir/recolher</strong></summary>

1. [🎯 Visão Geral](#-visão-geral)
2. [⚙️ Arquitetura da Solução](#️-arquitetura-da-solução)
3. [🚀 Como Funciona](#-como-funciona)
4. [🔧 Configuração do iFlow](#-configuração-do-iflow)
5. [📡 Testando com Postman](#-testando-com-postman)
6. [🔐 Segurança & Boas Práticas](#-segurança--boas-práticas)
7. [📦 Downloads](#-downloads)
8. [🤝 Contribuindo](#-contribuindo)

</details>

---

## 🎯 Visão Geral

O **Valorant Smart Advisor** é uma solução desenvolvida no **SAP Integration Suite (CPI)** que combina integração de APIs externas com inteligência artificial para gerar recomendações personalizadas dentro do jogo **Valorant**.

### ✨ Funcionalidades

| Entrada | Processamento | Saída |
|---------|--------------|-------|
| 🗺️ Mapa do jogo | 🔍 Consulta APIs públicas (maps, agents, weapons) | 🎯 Agente ideal |
| 🎮 Estilo de jogo | ⚙️ Regras de negócio com Groovy Scripts | 🔫 Arma recomendada |
| | 🧠 Lógica de recomendação contextual | 📋 Estratégia personalizada |
| | 🤖 IA Generativa para refinamento da resposta | 💡 Dica tática inteligente |

### 💡 O que esta solução demonstra na prática:

- ✅ **Integração REST** no SAP CPI com adapters HTTPS
- ✅ **Orquestração de múltiplas APIs** externas (Valorant API + OpenRouter)
- ✅ **Processamento dinâmico** com Groovy Scripts (parsing, scoring, lógica de domínio)
- ✅ **Padrão de integração com IA Generativa**: prompt engineering, parsing de resposta e fallback
- ✅ **Gestão de contexto** via Exchange Properties e Content Modifiers

---

## ⚙️ Arquitetura da Solução

![Fluxo](imagens/Screenshot_54.png)

## 🚀 Como Funciona

### 📥 Entrada da Requisição

```json
POST /valorant
{
  "map": "The Range",
  "playstyle": "defensive"
}
```

### 🎮 Valores suportados para playstyle

| Valor      | Descrição                                      |
|------------|-----------------------------------------------|
| aggressive | Foco em entradas rápidas e eliminações        |
| defensive  | Prioriza posicionamento e controle de área    |
| support    | Foco em utilidades e suporte ao time          |
| strategic  | Controle de ritmo e execução tática           |

---

### 🗺️ Mapas suportados

| Mapa 1     | Mapa 2     | Mapa 3     | Mapa 4     | Mapa 5     | Mapa 6     |
|------------|------------|------------|------------|------------|------------|
| Ascent     | Split      | Fracture   | Bind       | Breeze     | Haven      |
| Icebox     | Lotus      | Sunset     | Pearl      | The Range  | Piazza     |
| Corrode    | District   | Kasbah     | Drift      | Glitch     | Abyss      |
| Skirmish A | Skirmish B | Skirmish C | Basic Training |            |            |


## 🔄 Fluxo de Processamento

1. **Recepção**  Payload JSON recebido via HTTPS Adapter  

2. **Enriquecimento**  Consultas paralelas às APIs públicas do Valorant  

3. **Regras de Negócio**  Groovy Script aplica scoring de agentes e seleção de armas  

4. **IA Generativa**  Prompt estruturado enviado ao OpenRouter para refinamento  

5. **Resposta Final**  JSON formatado com recomendação completa + dica contextual  


## 📤 Saída da Recomendação
```
{
  "agente": "Cypher",
  "mapa": "The Range",
  "arma": "Vandal",
  "estilodojogo": "defensive",
  "dica": "Segure ângulos importantes e evite se expor. Priorize sobreviver e punir erros do inimigo no mapa The Range."
}
```

## 🔧 Configuração do iFlow

> **Package:** `ZPKG_Valorant_Smart_Advisor`  
> **iFlow:** `IFL_VALORANT_ADVISOR`

<br>

### 🧱 1. Criação do Package
![Fluxo](imagens/Screenshot_1.png)

Nome do Package:
```
ZPKG_Valorant_Smart_Advisor
```
![Fluxo](imagens/Screenshot_2.png)

### ➕ 2. Adição do Artefato iFlow
![Fluxo](imagens/Screenshot_3.png)

### 🏷️ 3. Nome do iFlow:
```
IFL_VALORANT_ADVISOR
```
![Fluxo](imagens/Screenshot_4.png)

### ➕ 4. Adicionando o Adapter
![Fluxo](imagens/Screenshot_5.png)

### 🔹 5. Configuração do Adapter HTTPS (Sender)


| Parâmetro    | Valor            |
|--------------|----------------- |
| Address      | /valorant        |
| Method       | POST             |
| Content-Type | application/json |
![Fluxo](imagens/Screenshot_6.png)



### ➕ 6. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_7.png)

### 🏷️ 7. Renomeando o Content Modifier
```
Nome: CM_originalBody
```
![Fluxo](imagens/Screenshot_8.png)

### 🔹 8. Content Modifier – Salvar Payload Original
Configuração – Exchange Properties:

| Name         | Source Type | Source Value | Data Type      |
| ------------ | ----------- | ------------ | -------------- |
| originalBody | Expression  | ${body}      |java.lang.String|

![Fluxo](imagens/Screenshot_9.png)












<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>







🔹 5. Request-Reply – Consulta APIs Externas
🗺️ Maps API

→

Parâmetro
Valor
Address
https://valorant-api.com/v1/maps
Query
language=pt-BR
Method
GET
Authentication
None
Content Modifier – getMaps:
Name
Source Type
Source Value
Data Type
maps
Expression
${body}
java.lang.String

🎮 Agents API

→

Parâmetro
Valor
Address
https://valorant-api.com/v1/agents
Query
language=pt-BR
Method
GET
Content Modifier – getAgents:
Name
Source Type
Source Value
Data Type
agents
Expression
${body}
java.lang.String

🔫 Weapons API

→

Parâmetro
Valor
Address
https://valorant-api.com/v1/weapons
Query
language=pt-BR
Method
GET
Content Modifier – getWeapons:
Name
Source Type
Source Value
Data Type
weapons
Expression
${body}
java.lang.String

🔹 6. Groovy Script – Regras de Negócio

Nome: GS_Valorant

📦 Código fonte:
Download do Groovy Script - GS_Valorant.groovy

🔹 7. Preparação para IA – Headers e Prompt
Content Modifier – CM_setValues_IA:

→

Header
Source Type
Source Value
Authorization
Constant
Bearer sk-or-v1-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
Content-Type
Constant
application/json
⚠️ Atenção: Substitua a chave de exemplo pela sua chave real do OpenRouter.
Groovy Script – GS_PreparePrompt:

→

📦 Código fonte:
Download do Groovy Script - GS_PreparePrompt.groovy
🔹 8. Request-Reply – Chamada à IA (OpenRouter)

→

Parâmetro
Valor
Nome
RR_IA
Address
https://openrouter.ai/api/v1/chat/completions
Method
POST
Authentication
None
Request Headers
* (herda do Content Modifier anterior)
🔹 9. Groovy Script – Parsing da Resposta da IA

Nome: GS_ParseAI

📦 Código fonte:
Download do Groovy Script - GS_ParseAI.groovy

🔹 10. Configuração Final do iFlow

🔐 Configuração OpenRouter
🔑 Gerenciamento de Chaves de API
URL: https://openrouter.ai/workspaces/default/keys

⚙️ Modelo Utilizado

Parâmetro
Valor
Model
nvidia/nemotron-3-super-120b-a12b:free
Temperature
0.3 (respostas mais determinísticas)
Output Format
JSON estruturado
📡 Testando com Postman
🎯 Payload – Estilo Agressivo
json
12345

🛡️ Payload – Estilo Defensivo
json
12345

🤝 Payload – Estilo Suporte
json
12345

🧠 Payload – Estilo Estratégico
json
12345

🔐 Segurança & Boas Práticas
⚠️ Importante: Este repositório é para fins educacionais e de demonstração.
🔒 Recomendações para Produção
Área
Situação Atual
Sugestão para Produção
🔑 API Keys
Hardcoded no Content Modifier
Usar SAP Credential Store ou BTP Destination Service
🔄 Resiliência
Sem fallback na chamada à IA
Adicionar Exception Subprocess com retry ou resposta estática
📦 Validação
Validação básica no Groovy
Incluir JSON Schema Validator antes do processamento
📊 Monitoramento
Logs via message.log
Integrar com SAP Cloud Integration Monitor + custom metrics
⚡ Performance
Chamadas sequenciais às APIs
Avaliar Multicast paralelo para maps/agents/weapons
♻️ Cache
Sem cache de dados estáticos
Usar Data Store do CPI para cache de 15-30min
🚫 Dados Sensíveis
Nunca commitar chaves de API reais no repositório
Utilizar variáveis de ambiente ou serviços seguros de credenciais
Rotacionar chaves periodicamente
📦 Downloads
🗂️ Pacote Completo do iFlow
📥 Download do iFlow – CPI_ZPKG_Valorant-Smart-Advisor-IA-CPI-
💡 Como importar no SAP CPI:
Acesse o SAP Integration Suite
Navegue até Monitor → Artifacts
Clique em Import e selecione o arquivo .zip
Configure as destinations/credenciais conforme necessário
Ative o iFlow e teste via Postman
📄 Scripts Groovy Individuais
Script
Função
Link
GS_Valorant.groovy
Regras de negócio, scoring e estratégia
Baixar
GS_PreparePrompt.groovy
Engenharia de prompt para IA
Baixar
GS_ParseAI.groovy
Parsing e formatação da resposta da IA
Baixar
🤝 Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para:
🍴 Fazer fork do projeto
🌿 Criar uma branch para sua feature (git checkout -b feature/AmazingFeature)
💾 Commitar suas alterações (git commit -m 'Add: AmazingFeature')
📤 Enviar para o repositório (git push origin feature/AmazingFeature)
🔓 Abrir um Pull Request
🐛 Encontrou um bug?
Abra uma Issue descrevendo o problema
Inclua passos para reproduzir e, se possível, logs do CPI
Sugira uma solução ou melhoria
📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
<p align="center">
<strong>Desenvolvido com 💙 para a comunidade SAP e gamers</strong><br>
<sub>Feito por <a href="https://github.com/souzajean">@souzajean</a> • SAP BTP • Valorant Smart Advisor</sub>
</p>

<p align="center">
<a href="#-valorant-smart-advisor--ia--sap-cpi">⬆️ Voltar ao topo</a>
</p>
```
