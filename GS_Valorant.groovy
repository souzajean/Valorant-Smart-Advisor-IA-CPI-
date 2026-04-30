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