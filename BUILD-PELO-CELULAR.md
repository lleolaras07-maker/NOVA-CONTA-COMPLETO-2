# Gerar o APK pelo celular

Este projeto está preparado para GitHub Actions.

## 1. Criar um repositório
No navegador do celular, entre no GitHub e crie um repositório novo, por exemplo:

NovaConta

Pode deixar como privado.

## 2. Enviar o ZIP
Extraia o ZIP no celular e envie os arquivos do projeto para o repositório.
O arquivo `.github/workflows/build-apk.yml` precisa ficar exatamente nesse caminho.

## 3. Iniciar a compilação
No GitHub:
- abra o repositório;
- toque em **Actions**;
- escolha **Build NovaConta APK**;
- toque em **Run workflow**.

## 4. Baixar
Quando terminar:
- abra a execução concluída;
- procure **Artifacts**;
- baixe **NovaConta-APK**;
- dentro dele estará `app-debug.apk`.

## Observação
O APK desta etapa é de demonstração/homologação. Ele não movimenta dinheiro real até que o backend de produção e o PSP sejam configurados.
