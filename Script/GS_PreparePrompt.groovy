import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def Message processData(Message message) {

    def slurper = new JsonSlurper()

    // 🔹 pega resultado do script anterior
    def currentBody = slurper.parseText(message.getBody(String))

    def mapName = currentBody.map
    def agents  = currentBody.agents
    def weapon  = currentBody.weapon
    def strategy = currentBody.strategy

    // 🔹 monta prompt
    def prompt = """
Você é um especialista em Valorant.

Baseado nos dados abaixo:

Mapa: ${mapName}
Estilo de jogo: ${currentBody.input.playstyle}

Agentes sugeridos: ${agents.join(", ")}
Arma sugerida: ${weapon}

Melhore essa recomendação.

Responda SOMENTE em JSON válido:
{
  "agente": "",
  "mapa": "",
  "arma": "",
  "strategy": ""
}
"""

    def requestBody = [
        model: "nvidia/nemotron-3-super-120b-a12b:free",
        messages: [
            [role: "user", content: prompt]
        ],
        temperature: 0.3
    ]

    message.setBody(JsonOutput.toJson(requestBody))
    return message
}