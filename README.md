# 💸 Vamos Rachar

Aplicativo Android que facilita a divisão de valores entre pessoas de forma rápida, intuitiva e acessível.

## ✨ Funcionalidades

### 🧮 Cálculo da Divisão de Dinheiro
- Permite inserir o **valor total** a ser dividido.
- Permite inserir a **quantidade de pessoas**.
- O valor por pessoa é calculado automaticamente e exibido em tempo real.

### 🔊 Leitura em Voz Alta (TTS)
- Utiliza **Text-to-Speech** para ler em voz alta o valor calculado.
- Útil para acessibilidade ou situações onde a leitura em grupo é prática.

### 📤 Compartilhamento de Resultado
- Permite **compartilhar** o valor calculado com outros aplicativos (WhatsApp, e-mail, etc.).

### 💾 Salvamento de Resultados
- Salva os cálculos realizados no banco de dados local.

### 📋 Visualização de Resultados Salvos
- Tela dedicada que lista todas as divisões salvas anteriormente.

### 📥 Recebimento de Texto Compartilhado
- Exibe informações recebidas de outros aplicativos por meio de `Intent`.

---

## 🛠 Tecnologias Utilizadas

- **Kotlin**
- **Android SDK**
- **SQLite** (via `DatabaseHelper`)
- **TextToSpeech** (Android TTS)
- **Android Intents** para compartilhamento e navegação
