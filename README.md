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

# 🔹 9. Groovy Script

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
📦 [Download do Groovy Script](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/raw/main/Package/IFL_VALORANT_ADVISOR.zip)
![Fluxo](imagens/Screenshot_32.png)




     



























<br>

# 🔹 12. Postman

### ➕ Enviando o Payload
📥 Enviando Payload   
- Method: **POST**   
- URL: **/order/classify**   
- Body:   
```
<Order>
  mount>
    <Region>SP</Region>
</Order>
```
![Fluxo](imagens/Screenshot_50.png)

<br>

### Resultado Payload
📤 Saída Payload
```
<ProcessedOrder>
    <OrderID>5001</OrderID>
    <CustomerID>2002</CustomerID>
    <Amo
```

![Fluxo](imagens/Screenshot_50.png)






<br>
<br>

---

## 📦 Exemplo prático – iFlow para baixar

📦 [Download do iFlow – CPI_ZPKG_Valorant-Smart-Advisor-IA-CPI-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/raw/main/Package/IFL_VALORANT_ADVISOR.zip)



