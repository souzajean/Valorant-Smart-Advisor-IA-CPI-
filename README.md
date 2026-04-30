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
![Fluxo](imagens/Screenshot_32.png)
```

import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {

    def slurper = new JsonSlurper()

    // 🔹 Input
    def originalBody = message.getProperty("originalBody")
    def input = safeParse(originalBody)

    if (!input) {
        throw new Exception("Erro ao ler input original (JSON inválido)")
    }

    def mapInput = input.map?.toLowerCase()?.trim()
    def playstyle = input.playstyle?.toLowerCase()?.trim()

    // 🔹 APIs
    def maps    = safeParse(message.getProperty("maps"))
    def agents  = safeParse(message.getProperty("agents"))
    def weapons = safeParse(message.getProperty("weapons"))

    if (!maps?.data)    throw new Exception("Erro ao carregar MAPS")
    if (!agents?.data)  throw new Exception("Erro ao carregar AGENTS")
    if (!weapons?.data) throw new Exception("Erro ao carregar WEAPONS")

    // 🔥 1. Validar mapa
    def selectedMap = maps.data.find {
        it.displayName?.toLowerCase() == mapInput
    }

    if (!selectedMap) {
        throw new Exception("Mapa inválido: " + mapInput)
    }

    // 🔥 2. Classificar mapa
    def mapType = classifyMap(mapInput)

    // 🔥 3. ROLE → PLAYSTYLE
    def roleToPlaystyle = [
        "duelist"   : "aggressive",
        "sentinel"  : "defensive",
        "initiator" : "support",
        "controller": "strategic"
    ]

    // 🔥 4. SCORE de agentes
    def scoredAgents = agents.data.collect { agent ->

        def score = 0
        def role = agent.role?.displayName?.toLowerCase()

        if (playstyle == "aggressive" && role == "duelist") score += 50
        if (playstyle == "defensive" && role == "sentinel") score += 50
        if (playstyle == "support" && role == "initiator") score += 50
        if (playstyle == "strategic" && role == "controller") score += 50

        // bônus por mapa
        if (mapType == "close" && role == "duelist") score += 20
        if (mapType == "open" && role == "controller") score += 20

        return [
            name : agent.displayName,
            score: score
        ]
    }

    // 🔥 TOP 3 agentes
    def topAgents = scoredAgents
        .sort { -it.score }
        .take(3)
        .collect { it.name }

    // 🔥 5. Escolha inteligente de arma
    def weaponCategoryMap = [
        "shotgun": ["Judge", "Bucky"],
        "sniper" : ["Operator", "Marshal"],
        "rifle"  : ["Vandal", "Phantom"]
    ]

    def weaponTypeByMap = [
        "close" : "shotgun",
        "open"  : "sniper",
        "mixed" : "rifle"
    ]

    def weaponType = weaponTypeByMap.get(mapType, "rifle")
    def possibleWeapons = weaponCategoryMap.get(weaponType, ["Vandal"])

    def weaponChoice = possibleWeapons[new Random().nextInt(possibleWeapons.size())]

    // 🔥 6. Estratégia dinâmica com agente
    def strategy = buildStrategy(playstyle, mapType, topAgents[0])

    // 🔥 7. Resposta final
    def recommendation = [
        map    : selectedMap.displayName,
        agents : topAgents,
        weapon : weaponChoice,
        strategy: strategy,
        input  : [
            map: mapInput,
            playstyle: playstyle
        ]
    ]

    message.setBody(JsonOutput.prettyPrint(JsonOutput.toJson(recommendation)))
    return message
}

//
// 🔥 SAFE PARSE (anti erro JSON)
//
def safeParse(String jsonText) {
    try {
        if (!jsonText) return null
        def text = jsonText.trim()
        if (!(text.startsWith("{") || text.startsWith("["))) return null
        return new JsonSlurper().parseText(text)
    } catch (Exception e) {
        return null
    }
}

// 🔥 Classificação de mapa
def classifyMap(mapName) {

    def closeMaps = ["bind", "split"]
    def openMaps  = ["breeze", "icebox"]

    if (closeMaps.contains(mapName)) return "close"
    if (openMaps.contains(mapName)) return "open"

    return "mixed"
}

// 🔥 Estratégia com agente
def buildStrategy(style, mapType, agent) {

    if (style == "aggressive") {
        return "Use ${agent} para entradas rápidas e pressão constante"
    }

    if (style == "defensive") {
        return "Utilize ${agent} para segurar posições estratégicas"
    }

    if (style == "support") {
        return "Use ${agent} para dar suporte ao time com utilidade"
    }

    if (style == "strategic") {
        return "Use ${agent} para controle de mapa e execução tática"
    }

    return "Adapte sua estratégia conforme a partida"
}

```






























### 🏷️ Renomeando o Groovy Script

```
GS_Classificacao
```

<br>

### ➕ Lógica do ordem de classificação
```
import com.sap.gateway

```

<br>

# 🔹 7. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_14.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: CM_XMLFinal
```
![Fluxo](imagens/Screenshot_15.png)


<br>

### ⚙️ Configuração do Content Modifier
Construindo a resposta final em XML
Adiciona timestamp (data/hora) e status   

📩 Message Body
- **Type:** Expression  
- **Body:**
```
<ProcessedOrder>
    <OrderID>${property.orderId}</OrderID>
    <CustomerID>${property.customerId}</CustomerID>
    
    <Amount>${property.amount}</Amount>
    <Region>${property.region}</Region>
    
    <Category>${property.category}</Category>
    
    <Status>${property.status}</Status>
    <ProcessedAt>${date:now:yyyy-MM-dd HH:mm:ss}</ProcessedAt>
</ProcessedOrder>
```
![Fluxo](imagens/Screenshot_16.png)

<br>

# 🔹 8. Groovy Script
Registra o payload final no Message Monitoring

📤 Payload de Saída
5001 2002 750 SP MEDIUM PROCESSED 2026-04-22 10:00:00   

🧠 Principais Funcionalidades   
✔️ Extração de dados baseada em XPath   
✔️ Regras de negócio com Script Groovy   
✔️ Transformação de XML utilizando Content Modifier   
✔️ Log de payload para monitoramento   
✔️ Pronto para externalização de parâmetros   

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_17.png)

<br>

### 🏷️ Renomeando o Groovy Script
![Fluxo](imagens/Screenshot_18.png)
```
GS_Log_Payload_Final
```

<br>

### ➕ Lógica do ordem de classificação

```
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def body = message.getBody(String)

    def messageLog = messageLogFactory.getMessageLog(message)
    if(messageLog != null){
        messageLog.addAttachmentAsString("Payload Final", body, "text/xml")
    }

    return message
}
```
![Fluxo](imagens/Screenshot_19.png)

<br>

# 🔹 9. Postman

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
![Fluxo](imagens/Screenshot_20.png)

<br>

### Resultado Payload
📤 Saída Payload
```
<ProcessedOrder>
    <OrderID>5001</OrderID>
    <CustomerID>2002</CustomerID>
    <Amo
```

![Fluxo](imagens/Screenshot_21.png)

<br>

# 🔹 8. Monitoramento
### Monitor Message Processamento
![Fluxo](imagens/Screenshot_22.png)

<br>

### Log de anexo do Monitor Message
![Fluxo](imagens/Screenshot_23.png)

<br>
<br>

---

## 📦 Exemplo prático – iFlow para baixar

📦 [Download do iFlow – CPI_ZPKG_Valorant-Smart-Advisor-IA-CPI-](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/raw/main/Package/IFL_VALORANT_ADVISOR.zip)



