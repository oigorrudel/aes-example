# aes-example

Exemplo simples de aplicação/biblioteca para demonstrar criptografia simétrica usando **AES (Advanced Encryption Standard)** em Java.

---

## Visão Geral

Este projeto tem como objetivo ilustrar como usar o algoritmo AES para:

- Criptografar e descriptografar strings ou dados binários. 

É ideal para estudos, aprendizado de criptografia, ou para servir como base em projetos que necessitam de criptografia.

---

## Tecnologias e Bibliotecas

- Java (versão compatível com JCA)  
- `javax.crypto.Cipher`, `SecretKeySpec` (API padrão de criptografia do Java)  
- Base64 para codificação de dados criptografados

---

## Como Usar

### 1. Gerar / configurar uma chave AES

Você pode gerar uma chave AES no Java usando `KeyGenerator`:

```java
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
keyGen.init(128); // ou 192, 256 (depende da política de criptografia)
SecretKey secretKey = keyGen.generateKey();
```
