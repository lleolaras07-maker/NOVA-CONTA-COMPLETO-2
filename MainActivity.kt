package com.novaconta.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF16233A)
private val Green = Color(0xFF18A874)
private val Bg = Color(0xFFF7F8FA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NovaContaApp() }
    }
}

@Composable
fun NovaContaApp() {
    var logged by remember { mutableStateOf(false) }
    if (!logged) LoginScreen { logged = true } else MainScreen()
}

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize(), color = Bg) {
        Column(
            modifier = Modifier.fillMaxSize().padding(28.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("NovaConta", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Navy)
            Text("Seu dinheiro. Do seu jeito.", color = Color.Gray, fontSize = 16.sp)
            Spacer(Modifier.height(40.dp))
            OutlinedTextField(email, { email = it }, label = { Text("E-mail") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(pin, { pin = it }, label = { Text("Senha / PIN") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(22.dp))
            Button(onClick = onLogin, modifier = Modifier.fillMaxWidth().height(52.dp), colors = ButtonDefaults.buttonColors(containerColor = Navy)) {
                Text("Entrar")
            }
            Spacer(Modifier.height(12.dp))
            Text("Modo demonstração", color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun MainScreen() {
    var tab by remember { mutableIntStateOf(0) }
    Scaffold(
        containerColor = Bg,
        bottomBar = {
            NavigationBar {
                listOf(
                    Icons.Default.Home to "Início",
                    Icons.Default.QrCode to "Pix",
                    Icons.Default.ReceiptLong to "Extrato",
                    Icons.Default.Person to "Perfil"
                ).forEachIndexed { i, pair ->
                    NavigationBarItem(selected = tab == i, onClick = { tab = i }, icon = { Icon(pair.first, null) }, label = { Text(pair.second) })
                }
            }
        }
    ) { pad ->
        when (tab) {
            0 -> Home(pad)
            1 -> Pix(pad)
            2 -> Statement(pad)
            else -> Profile(pad)
        }
    }
}

@Composable
fun Home(pad: PaddingValues) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(pad).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Olá!", color = Color.Gray)
                    Text("Bem-vindo à NovaConta", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Navy)
                }
                Box(Modifier.size(44.dp).background(Navy, CircleShape), contentAlignment = Alignment.Center) {
                    Text("NC", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = Navy), shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(22.dp)) {
                    Text("Saldo disponível", color = Color.White.copy(alpha = .75f))
                    Text("R$ 2.450,00", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(14.dp))
                    Text("Conta NovaConta •••• 4821", color = Color.White.copy(alpha = .7f))
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                QuickAction(Icons.Default.QrCode, "Pix", Modifier.weight(1f))
                QuickAction(Icons.Default.Payment, "Pagar", Modifier.weight(1f))
                QuickAction(Icons.Default.Send, "Enviar", Modifier.weight(1f))
            }
        }
        item {
            Text("Cartão virtual", fontWeight = FontWeight.Bold, color = Navy, fontSize = 18.sp)
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF253553)), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth().height(190.dp)) {
                Column(Modifier.padding(22.dp), verticalArrangement = Arrangement.SpaceBetween) {
                    Text("NOVACONTA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(Modifier.height(50.dp))
                    Text("••••  ••••  ••••  4821", color = Color.White, fontSize = 19.sp)
                    Text("VIRTUAL", color = Color.White.copy(alpha=.65f), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun QuickAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, modifier: Modifier) {
    Card(modifier = modifier.height(92.dp), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(icon, null, tint = Green)
            Spacer(Modifier.height(6.dp))
            Text(label, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun Pix(pad: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(pad).padding(20.dp)) {
        Text("Pix", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Navy)
        Text("Envie e receba dinheiro instantaneamente.", color = Color.Gray)
        Spacer(Modifier.height(22.dp))
        listOf("Enviar Pix", "Receber Pix", "Cobrar com QR Code", "Chaves Pix").forEach {
            Card(Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable { }, shape = RoundedCornerShape(16.dp)) {
                Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.QrCode, null, tint = Green)
                    Spacer(Modifier.width(14.dp))
                    Text(it, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.weight(1f))
                    Icon(Icons.Default.ChevronRight, null)
                }
            }
        }
    }
}

@Composable
fun Statement(pad: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(pad).padding(20.dp)) {
        Text("Extrato", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Navy)
        Spacer(Modifier.height(18.dp))
        listOf(
            "Pix recebido" to "+ R$ 850,00",
            "Pagamento" to "- R$ 79,90",
            "Pix enviado" to "- R$ 120,00",
            "Depósito" to "+ R$ 500,00"
        ).forEach { (a,b) ->
            Card(Modifier.fillMaxWidth().padding(vertical = 5.dp)) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountBalanceWallet, null, tint = Green)
                    Spacer(Modifier.width(12.dp))
                    Text(a, Modifier.weight(1f))
                    Text(b, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun Profile(pad: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(pad).padding(20.dp)) {
        Text("Perfil", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Navy)
        Spacer(Modifier.height(20.dp))
        Text("Configurações", fontWeight = FontWeight.Bold)
        listOf("Dados pessoais", "Segurança", "Limites", "Notificações", "Ajuda").forEach {
            ListItem(headlineContent = { Text(it) }, leadingContent = { Icon(Icons.Default.Settings, null) })
        }
        Spacer(Modifier.height(12.dp))
        Text("NovaConta 1.0.0 • Protótipo", color = Color.Gray, fontSize = 12.sp)
    }
}
