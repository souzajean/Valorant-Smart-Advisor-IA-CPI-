import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {

    def slurper = new JsonSlurper()
    def body = message.getBody(String)

    def json = slurper.parseText(body)

    def content = json?.choices?.getAt(0)?.message?.content

    if (!content) {
        throw new Exception("Resposta da IA inválida")
    }

    // 🔥 limpa markdown
    content = content.replaceAll("```json", "")
                     .replaceAll("```", "")
                     .trim()

    def parsed = slurper.parseText(content)

    // 🔹 pega playstyle original
    def originalBody = message.getProperty("originalBody")
    def input = slurper.parseText(originalBody)
    def playstyle = input.playstyle ?: "aggressive"

    // 🔥 gera dica inteligente
    def dica = buildTip(playstyle, parsed.mapa)

    // 🔥 resposta final
    def finalResponse = [
        agente: parsed.agente ?: "Desconhecido",
        mapa: parsed.mapa ?: "Desconhecido",
        arma: parsed.arma ?: "Desconhecido",
        estilodojogo: playstyle,
        dica: dica
    ]

    message.setBody(JsonOutput.prettyPrint(JsonOutput.toJson(finalResponse)))
    return message
}


// 🔥 GERADOR DE DICA (SEU DIFERENCIAL)
def buildTip(style, map) {

    if (style == "aggressive") {
        return "Jogue avançando rapidamente, buscando eliminações iniciais e pressionando o inimigo em pontos estratégicos do mapa ${map}."
    }

    if (style == "defensive") {
        return "Segure ângulos importantes e evite se expor. Priorize sobreviver e punir erros do inimigo no mapa ${map}."
    }

    if (style == "support") {
        return "Apoie seu time com habilidades, visão e cura. Sua função é manter o time vivo e controlar o mapa ${map}."
    }

    if (style == "strategic") {
        return "Controle o ritmo da partida usando utilidades e posicionamento inteligente no mapa ${map}."
    }

    return "Adapte sua estratégia conforme a partida no mapa ${map}."
}