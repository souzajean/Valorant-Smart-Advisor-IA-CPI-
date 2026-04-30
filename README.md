# Valorant-Smart-Advisor-IA-CPI-
SAP BTP CPI - Valorant Smart Advisor

<br>

![Fluxo](imagens/capa-linkedin.png)

---

<br>

# 🏗️ 🔧 Arquitetura do iFlow

<br><br>

# 🔄 1. Fluxo da Integração

<br>

### 🧱 Criando o Package
![Fluxo](imagens/Screenshot_1.png)

<br><br>

### 🏷️ Nome do Package
```
ZPKG_Valorant_Smart_Advisor
```
![Fluxo](imagens/Screenshot_2.png)

<br>

### ➕ Adicionando o Artefato
![Fluxo](imagens/Screenshot_3.png)

<br>

### 🏷️ Nome do iFlow
```
IFL_VALORANT_ADVISOR
```
![Fluxo](imagens/Screenshot_4.png)

<br>

### ➕ Adicionando o Adapter
![Fluxo](imagens/Screenshot_5.png)

<br> 

# 🔹 2. HTTPS Sender (Trigger)
```
Endpoint: /valorant
```
![Fluxo](imagens/Screenshot_6.png)

<br>

# 🔹 3. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_7.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: CM_originalBody
```
![Fluxo](imagens/Screenshot_8.png)


<br>

### ⚙️ Configuração do Content Modifier
📩 Exchange Properties
```
| Name        | Source Type | Source Value        | Data Type        |
|-------------|-------------|---------------------|------------------|
| originalBody| Expression  | ${body}             | java.lang.String |


```
![Fluxo](imagens/Screenshot_9.png)

<br>

# 🔹 4. Request Replay

### ➕ Adicionando o Request Replay
![Fluxo](imagens/Screenshot_10.png)

<br>

### ➕ Adicionando HTTPS
![Fluxo](imagens/Screenshot_11.png)

<br>

### ⚙️ Configuração do Request Replay
![Fluxo](imagens/Screenshot_12.png)
```
Address: https://valorant-api.com/v1/maps
Query: language=pt-BR
Method: GET
Authentication: None
```
# 🔹 5. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_13.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: getMaps
```
![Fluxo](imagens/Screenshot_14.png)


<br>

### ⚙️ Configuração do Content Modifier
📩 Exchange Properties
```
| Name        | Source Type | Source Value        | Data Type        |
|-------------|-------------|---------------------|------------------|
| maps        | Expression  | ${body}             | java.lang.String |


```
![Fluxo](imagens/Screenshot_15.png)

<br>


# 🔹 6. Request Replay

### ➕ Adicionando o Request Replay
![Fluxo](imagens/Screenshot_16.png)

<br>

### ➕ Adicionando Receiver
![Fluxo](imagens/Screenshot_17.png)

<br>

### ➕ Adicionando HTTPS
![Fluxo](imagens/Screenshot_18.png)

<br>

### ⚙️ Configuração do Request Replay
![Fluxo](imagens/Screenshot_19.png)
```
Address: https://valorant-api.com/v1/agents
Query: language=pt-BR
Method: GET
Authentication: None
```

# 🔹 7. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_20.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: getAgents
```

### ⚙️ Configuração do Content Modifier
📩 Exchange Properties
```
| Name        | Source Type | Source Value        | Data Type        |
|-------------|-------------|---------------------|------------------|
| agents      | Expression  | ${body}             | java.lang.String |
```
![Fluxo](imagens/Screenshot_21.png)

<br>

# 🔹 8. Request Replay

### ➕ Adicionando o Request Replay
![Fluxo](imagens/Screenshot_22.png)

<br>

### 🏷️ Renomeando o Request Replay
```
Nome: RR_Weapons
```
![Fluxo](imagens/Screenshot_23.png)

<br>

### ➕ Adicionando Receiver
![Fluxo](imagens/Screenshot_24.png)

<br>

### ➕ Adicionando HTTPS
![Fluxo](imagens/Screenshot_25.png)

<br>

### ⚙️ Configuração do Content Modifier
![Fluxo](imagens/Screenshot_26.png)
```
Address: https://valorant-api.com/v1/weapons
Query: language=pt-BR
Method: GET
Authentication: None
```
<br>

# 🔹 9. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_27.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: getWeapons
```
![Fluxo](imagens/Screenshot_28.png)

<br>

### ⚙️ Configuração do Content Modifier
📩 Exchange Properties
```
| Name         | Source Type | Source Value        | Data Type        |
|--------------|-------------|---------------------|------------------|
| weapons      | Expression  | ${body}             | java.lang.String |
```
![Fluxo](imagens/Screenshot_29.png)

<br>

# 🔹 10. Groovy Script

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_30.png)

<br>

### 🏷️ Renomeando o Groovy Script
![Fluxo](imagens/Screenshot_31.png)
```
GS_Valorant
```
<br>

### ➕ Lógica do ordem de classificação
Link do Código
📦 [Download do Groovy Script - GS_Valorant](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_Valorant.groovy)
![Fluxo](imagens/Screenshot_32.png)

<br>

# 🔹 11. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_33.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Mame: CM_setValues_IA
```
![Fluxo](imagens/Screenshot_34.png)

<br>

### ⚙️ Configuração do Content Modifier
📩 Message Headers
```
| Name           | Source Type | Source Value                                     |
|----------------|-------------|--------------------------------------------------|
| Authorization  | Constant    | Bearer sk-or-v1-00000000000000000000000000000000 |
| Content-Type   | Constant    | application/json                                 |
```
![Fluxo](imagens/Screenshot_35.png)

<br>

# 🔹 12. Groovy Script

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_36.png)

<br>

### 🏷️ Renomeando o Groovy Script
![Fluxo](imagens/Screenshot_37.png)
```
GS_PreparePrompt
```
<br>

### ➕ Lógica do ordem de classificação
Link do Código
📦 [Download do Groovy Script - GS_PreparePrompt](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_PreparePrompt.groovy)

![Fluxo](imagens/Screenshot_38.png)

<br>

# 🔹 13. Request Replay

### ➕ Adicionando o Request Replay
![Fluxo](imagens/Screenshot_39.png)

<br>

### 🏷️ Renomeando o Request Replay
```
Nome: RR_IA
```
![Fluxo](imagens/Screenshot_40.png)

<br>

### ➕ Adicionando Receiver
![Fluxo](imagens/Screenshot_41.png)

<br>

### ➕ Adicionando HTTPS
![Fluxo](imagens/Screenshot_42.png)

<br>

### ⚙️ Configuração do Content Modifier
![Fluxo](imagens/Screenshot_43.png)
```
Address: https://openrouter.ai/api/v1/chat/completions
Method: POST
Authentication None

Request Headers: *
```

<br>
	
# 🔹 14. Groovy Script

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_44.png)

<br>

### 🏷️ Renomeando o Groovy Script
![Fluxo](imagens/Screenshot_45.png)
```
GS_ParseAI
```
<br>

### ➕ Lógica do ordem de classificação
Link do Código
📦 [Download do Groovy Script - GS_ParseAI](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_ParseAI.groovy)

![Fluxo](imagens/Screenshot_46.png)

<br>

### ⚙️ Configuração final iflow
![Fluxo](imagens/Screenshot_47.png)

<br>

# 🔹 15. Site OpenRouter

### ⚙️ Configuração OpenRouter
```
https://openrouter.ai/workspaces/default/keys
```
![Fluxo](imagens/Screenshot_48.png)

<br>

### ⚙️ Configuração OpenRouter
![Fluxo](imagens/Screenshot_49.png)

<br>

<br>

# 🔹 16. Postman

### ➕ Enviando o Payload
📥 Enviando Payload Aggressive
- Method: **POST**   
- URL: **/valorant**   
- Body:   
```
{
  "map": "District",
  "playstyle": "aggressive"
}
```
![Fluxo](imagens/Screenshot_50.png)

<br>

### Resultado Payload
📤 Saída Payload
```
{
    "agente": "Fade",
    "mapa": "Fracture",
    "arma": "Phantom",
    "estilodojogo": "aggressive",
    "dica": "Jogue avançando rapidamente, buscando eliminações iniciais e pressionando o inimigo em pontos estratégicos do mapa Fracture."
}
```


### ➕ Enviando o Payload
📥 Enviando Payload defensive
- Method: **POST**   
- URL: **/valorant**   
- Body:   
```
{
  "map": "District",
  "playstyle": "defensive"
}
```
![Fluxo](imagens/Screenshot_51.png)

<br>

### Resultado Payload
📤 Saída Payload defensive
```
{
    "agente": "Fade",
    "mapa": "Fracture",
    "arma": "Phantom",
    "estilodojogo": "defensive",
    "dica": "Jogue avançando rapidamente, buscando eliminações iniciais e pressionando o inimigo em pontos estratégicos do mapa Fracture."
}
```
![Fluxo](imagens/Screenshot_51.png)






<br>
<br>

---

## 📦 Exemplo prático – iFlow para baixar

📦 [Download do iFlow – CPI_ZPKG_Valorant-Smart-Advisor-IA-CPI-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/raw/main/Package/IFL_VALORANT_ADVISOR.zip)



