



<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>









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

## 📄 Scripts Groovy Individuais

| Script                  | Função                                   | Link                                                                                                           |
| ----------------------- | ---------------------------------------- | -------------------------------------------------------------------------------------------------------------- | 
| GS_Valorant.groovy      | Regras de negócio, scoring e estratégia  | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_Valorant.groovy)      |
| GS_PreparePrompt.groovy | Engenharia de prompt para IA             | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_PreparePrompt.groovy) |
| GS_ParseAI.groovy       | Parsing e formatação da resposta da IA   | [Baixar](https://github.com/souzajean/Valorant-Smart-Advisor-IA-CPI-/blob/main/Script/GS_ParseAI.groovy)       |





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



<br>
<br>

---

## 📦 Exemplo prático – iFlow para baixar

📦 [Download do iFlow – CPI_ZPKG_ORDER_CLASSIFICATION](https://github.com/souzajean/ZPKG_ORDER_CLASSIFICATION/raw/main/Package/IFL_ORDER_CLASSIFICATION_XMLMODIFIER.zip)

