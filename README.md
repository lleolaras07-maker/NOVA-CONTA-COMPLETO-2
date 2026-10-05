# NovaConta — Android

Aplicativo Android de demonstração/protótipo de banco digital, com identidade própria.

## Abrir no Android Studio
1. Extraia o ZIP.
2. Abra a pasta `NovaConta-Android`.
3. Aguarde o Gradle sincronizar.
4. Selecione o módulo `app`.
5. Use **Build > Build APK(s)**.
6. O APK será gerado em `app/build/outputs/apk/debug/`.

## Funcionalidades
- Login demo
- Dashboard e saldo
- Pix
- Pagamentos
- Cartão virtual demonstrativo
- Extrato
- Perfil/configurações
- Arquitetura preparada para conexão com backend

## API
A URL base está em `app/build.gradle.kts` como `BuildConfig.API_BASE_URL`.
Por padrão é `https://api.novaconta.example`.

Para produção, altere para o domínio HTTPS real do backend.

IMPORTANTE: o APK nunca deve conter Client Secret, certificado privado da Efí ou outros segredos do PSP.
