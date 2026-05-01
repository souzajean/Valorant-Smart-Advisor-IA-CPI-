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

### 🔹 1. Criação do Package
![Fluxo](imagens/Screenshot_1.png)

Nome do Package:
```
ZPKG_Valorant_Smart_Advisor
```
![Fluxo](imagens/Screenshot_2.png)

### 🔹 2. Adição do Artefato iFlow
![Fluxo](imagens/Screenshot_3.png)

### 🔹 3. Nome do iFlow:
```
IFL_VALORANT_ADVISOR
```
![Fluxo](imagens/Screenshot_4.png)

### 🔹 4. Adicionando o Adapter
![Fluxo](imagens/Screenshot_5.png)

### 🔹 5. Configuração do Adapter HTTPS (Sender)


| Parâmetro    | Valor            |
|--------------|----------------- |
| Address      | /valorant        |
| Method       | POST             |
| Content-Type | application/json |
![Fluxo](imagens/Screenshot_6.png)



### 🔹 6. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_7.png)

### 🔹 7. Renomeando o Content Modifier
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


### 🔹 9. Adicionando o Request-Reply
![Fluxo](imagens/Screenshot_10.png)

### 🔹 10. Adicionando o Adapter
![Fluxo](imagens/Screenshot_11.png)

### 🔹 11. Request-Reply – Consulta APIs Externas

### 🗺️ Maps API →

| Parâmetro       | Valor                            | 
| --------------- | -------------------------------- |
|Address          | https://valorant-api.com/v1/maps |
| Query           | language=pt-BR                   | 
| Method          | GET                              |
|Authentication   | None                             |
| Request Headers | *                                |

![Fluxo](imagens/Screenshot_12.png)

### 🔹 12. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_13.png)

### 🔹 13. Renomeando o Content Modifier
```
Nome: CM_originalBody
```
![Fluxo](imagens/Screenshot_14.png)

### 🔹 14. Content Modifier – getMaps:
| Name  | Source Type | Source Value | Data Type        |
| ----- | ----------- | ------------ | ---------------- |
| maps  | Expression  | ${body}      | java.lang.String |

![Fluxo](imagens/Screenshot_15.png)


### 🔹 15. Adicionando o Request-Reply
![Fluxo](imagens/Screenshot_16.png)

### 🔹 16. Adicionando o Receiver
![Fluxo](imagens/Screenshot_17.png)

### 🔹 17. Adicionando o HTTP
![Fluxo](imagens/Screenshot_18.png)

### 🔹 18. Adicionando o HTTP
![Fluxo](imagens/Screenshot_18.png)

### 🔹 19. Request-Reply – Consulta APIs Externas

### 🗺️ Agents API →

| Parâmetro     | Valor                              | 
| ------------- | ---------------------------------- |
|Address        | https://valorant-api.com/v1/agents |
| Query         | language=pt-BR                     | 
| Method        | GET                                |
|Authentication | None                               |
![Fluxo](imagens/Screenshot_19.png)


### 🔹 20. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_20.png)


### 🔹 21. Renomeando o Content Modifier
```
Nome: getAgents
```
Content Modifier – getAgents:
| Name   | Source Type | Source Value | Data Type        |
| ------ | ----------- | ------------ | ---------------- |
| agents | Expression  | ${body}      | java.lang.String |

![Fluxo](imagens/Screenshot_21.png)


### 🔹 22. Adicionando o Request-Reply
![Fluxo](imagens/Screenshot_22.png)


### 🔹 23. Renomeando Request-Reply
```
RR_Weapons
```
![Fluxo](imagens/Screenshot_23.png)


### 🔹 24. Adicionando o Receiver
![Fluxo](imagens/Screenshot_24.png)


### 🔹 25. Adicionando o HTTP
![Fluxo](imagens/Screenshot_25.png)


### 🔹 26. Adicionando o HTTP
### 🔫 Weapons API →

| Parâmetro     | Valor                               | 
| ------------- | ----------------------------------- |
| Address       | https://valorant-api.com/v1/weapons | 
| Query         | language=pt-BR                      |
| Method        | GET                                 | 
![Fluxo](imagens/Screenshot_26.png)

### 🔹 27. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_27.png)


### 🔹 28. Renomeando o Content Modifier
```
Nome: getWeapons
```
![Fluxo](imagens/Screenshot_28.png)


Content Modifier – getWeapons:
| Name   | Source Type | Source Value | Data Type        |
| ------ | ----------- | ------------ | ---------------- |
| weapons| Expression  | ${body}      | java.lang.String |
![Fluxo](imagens/Screenshot_29.png)


🔹 29. Adicionando o Groovy Script
![Fluxo](imagens/Screenshot_30.png)

🔹 30. Renomeando o Groovy Script
```
GS_Valorant
```
![Fluxo](imagens/Screenshot_31.png)


🔹 31. Groovy Script – Regras de Negócio

### 📦 Baixar Groovy Script

📦 [Download do Groovy Script - GS_Valorant-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_Valorant.groovy)

![Fluxo](imagens/Screenshot_32.png)



### 🔹 33. Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_33.png)

### 🔹 34. Renomeando o Content Modifier
```
Nome: CM_setValues_IA
```
![Fluxo](imagens/Screenshot_34.png)


### 🔹 35. Preparação para IA – Headers e Prompt
Content Modifier – CM_setValues_IA: → **Message Header**

| Header        | Source Type | Source Value                                     |
| ------------- | ----------- | ------------------------------------------------ |
| Authorization | Constant    | Bearer sk-or-v1-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| Content-Type  | Constant    | application/json                                 |

⚠️ Atenção: Substitua a chave de exemplo pela sua chave real do OpenRouter.
![Fluxo](imagens/Screenshot_35.png)



🔹 36. Adicionando o Groovy Script
![Fluxo](imagens/Screenshot_36.png)

🔹 37. Renomeando o Groovy Script
```
GS_PreparePrompt
```
![Fluxo](imagens/Screenshot_37.png)


🔹 38. Groovy Script – Regras de Negócio

### 📦 Baixar Groovy Script

📦 [Download do Groovy Script – GS_PreparePrompt:-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_PreparePrompt.groovy)

![Fluxo](imagens/Screenshot_38.png)



### 🔹 39. Adicionando o Request-Reply
![Fluxo](imagens/Screenshot_39.png)


### 🔹 40. Renomeando Request-Reply
```
RR_IA
```
![Fluxo](imagens/Screenshot_40.png)


### 🔹 41. Adicionando o Receiver
![Fluxo](imagens/Screenshot_41.png)


### 🔹 42. Adicionando o HTTP
![Fluxo](imagens/Screenshot_42.png)


### 🔹 43. Adicionando o HTTP
### 🔫 Weapons API →
Request-Reply – Chamada à IA (OpenRouter)

| Parâmetro      | Valor                                         |
| -------------- | --------------------------------------------- |
| Nome           | RR_IA                                         |
| Address        | https://openrouter.ai/api/v1/chat/completions |
| Method         | POST                                          |
|Authentication  | None                                          |
|Request Headers | *                                             |
![Fluxo](imagens/Screenshot_43.png)


🔹 44. Adicionando o Groovy Script
![Fluxo](imagens/Screenshot_44.png)

🔹 45. Renomeando o Groovy Script
```
GS_ParseAI
```
![Fluxo](imagens/Screenshot_45.png)



🔹 46. Groovy Script – Regras de Negócio

### 📦 Baixar Groovy Script

📦 [Download do Groovy Script – GS_ParseAI:-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_ParseAI.groovy)

![Fluxo](imagens/Screenshot_46.png)



🔹 47. Configuração Final do iFlow
![Fluxo](imagens/Screenshot_47.png)


### 🔹 48. Configuração OpenRouter
```
URL: https://openrouter.ai/workspaces/default/keys
```

⚙️ Modelo Utilizado

| Parâmetro   | Valor                                  |
| ----------- | -------------------------------------- |
| Model       | nvidia/nemotron-3-super-120b-a12b:free |
| Temperature | 0.3                                    |

![Fluxo](imagens/Screenshot_48.png)



### 🔹 49. Gerenciamento de Chaves de API
![Fluxo](imagens/Screenshot_49.png)



## 📡 Testando com Postman

### 🎯 Payload – Estilo Agressivo
```json
{
  "map": "District",
  "playstyle": "aggressive"
}
```
![Fluxo](imagens/Screenshot_50.png)

### 🛡️ Payload – Estilo Defensivo
```json
{
  "map": "The Range",
  "playstyle": "defensive"
}
```
![Fluxo](imagens/Screenshot_51.png)


### 🤝 Payload – Estilo Suporte
```json
{
  "map": "Piazza",
  "playstyle": "support"
}
```
![Fluxo](imagens/Screenshot_52.png)

### 🧠 Payload – Estilo Estratégico
```json
{
  "map": "Corrode",
  "playstyle": "strategic"
}
```
![Fluxo](imagens/Screenshot_53.png)



## 🔐 Segurança & Boas Práticas
⚠️ Importante: Este repositório é para fins educacionais e de demonstração.

### 🔒 Recomendações para Produção


| Área              | Situação Atual                                    | Sugestão para Produção                                   |
| ----------------- | ------------------------------------------------- |--------------------------------------------------------- |
| 🔑 API Keys       | Hardcoded no Content Modifier                     | Usar SAP Credential Store ou BTP Destination Service     |
| 🔄 Resiliência    | Sem fallback na chamada à IA                      | Adicionar Exception Subprocess com retry ou fallback     |
| 📦 Validação      | Validação básica no Groovy                        | Incluir JSON Schema Validator antes do processamento     |
| 📊 Monitoramento  | Logs via message.log                              | Integrar com SAP Cloud Integration Monitor + métricas    |
| ⚡ Performance    | Chamadas sequenciais às APIs                      | Avaliar Multicast paralelo                               |
| ♻️ Cache          | Sem cache de dados estáticos                      | Usar Data Store do CPI (cache 15–30 min)                 |
|🚫 Dados Sensíveis | Nunca commitar chaves de API reais no repositório | Utilizar variáveis de ambiente ou serviços seguros de credenciais | Rotacionar chaves periodicamente |


---

## 📦 Downloads
| Nome                         | Link                                                                                                                      |
| ---------------------------- | -------------------------------------------------------------------------------------------------------------------       |
| 🗂️ Pacote Completo do iFlow | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Package/ZPKG_Valorant_Smart_Advisor.zip)    |
|📥 Download do iFlow         | [Baixar](https://github.com/souzajean/ZPKG_ORDER_CLASSIFICATION/raw/main/Package/IFL_ORDER_CLASSIFICATION_XMLMODIFIER.zip) |




## 💡 Como importar no SAP CPI:
- Acesse o SAP Integration Suite
- Navegue até Monitor → Artifacts
- Clique em Import e selecione o arquivo .zip
- Configure as destinations/credenciais conforme necessário
- Ative o iFlow e teste via Postman

## 📄 Scripts Groovy Individuais

| Script                  | Função                                   | Link                                                                                                           |
| ----------------------- | ---------------------------------------- | -------------------------------------------------------------------------------------------------------------- | 
| GS_Valorant.groovy      | Regras de negócio, scoring e estratégia  | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_Valorant.groovy)      |
| GS_PreparePrompt.groovy | Engenharia de prompt para IA             | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_PreparePrompt.groovy) |
| GS_ParseAI.groovy       | Parsing e formatação da resposta da IA   | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_ParseAI.groovy)       |




---
## 🤝 Contribuindo   
Contribuições são bem-vindas! Sinta-se à vontade para:


📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

---

<p align="center">
<strong>Desenvolvido Jean Souza</strong><br>
<sub>Feito por <a href="https://github.com/souzajean">@souzajean</a> • SAP BTP • Valorant Smart Advisor</sub>
</p>

<p align="center">
<a href="#-valorant-smart-advisor--ia--sap-cpi">⬆️ Voltar ao topo</a>
</p>
```
