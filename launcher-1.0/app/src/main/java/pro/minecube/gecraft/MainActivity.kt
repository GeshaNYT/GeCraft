package pro.minecube.gecraft
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private val Navy=Color(0xFF071426); private val Panel=Color(0xFF10243B); private val Blue=Color(0xFF42B8FF)
class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);val prefs=getSharedPreferences("gecraft",MODE_PRIVATE)
 setContent {
  var nick by remember { mutableStateOf(prefs.getString("nickname","") ?: "") };var saved by remember { mutableStateOf(nick.isNotBlank()) };var note by remember { mutableStateOf("Offline Account — локальный профиль") }
  MaterialTheme(colorScheme=darkColorScheme(primary=Blue,background=Navy,surface=Panel)){Surface(Modifier.fillMaxSize(),color=Navy){
   Column(Modifier.fillMaxSize().padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally){
    Spacer(Modifier.height(24.dp));Text("GeCraft",color=Blue,fontSize=38.sp,fontWeight=FontWeight.Black);Text("LAUNCHER 1.0 • PRE-RELEASE 2",color=Color.LightGray,fontSize=12.sp);Spacer(Modifier.height(32.dp))
    Card(Modifier.fillMaxWidth(),colors=CardDefaults.cardColors(containerColor=Panel),shape=RoundedCornerShape(22.dp)){Column(Modifier.padding(20.dp)){
     Text("OFFLINE ACCOUNT",color=Blue,fontWeight=FontWeight.Bold);Spacer(Modifier.height(12.dp))
     OutlinedTextField(value=nick,onValueChange={nick=it.take(16);saved=false},label={Text("Имя игрока")},singleLine=true,keyboardOptions=KeyboardOptions(capitalization=KeyboardCapitalization.None,keyboardType=KeyboardType.Ascii),modifier=Modifier.fillMaxWidth())
     Spacer(Modifier.height(12.dp));Button(onClick={if(nick.matches(Regex("[A-Za-z0-9_]{3,16}"))){prefs.edit().putString("nickname",nick).apply();saved=true;note="Профиль сохранён на устройстве"}else note="Ник: 3–16 символов A–Z, 0–9 или _"},modifier=Modifier.fillMaxWidth()){Text("Сохранить профиль")}
     if(saved)Text("✓ "+nick,color=Color(0xFF72E6A2),modifier=Modifier.padding(top=8.dp))
    }}
    Spacer(Modifier.height(16.dp));Card(Modifier.fillMaxWidth(),colors=CardDefaults.cardColors(containerColor=Panel),shape=RoundedCornerShape(22.dp)){Column(Modifier.padding(20.dp)){
     Text("MINECRAFT",color=Blue,fontWeight=FontWeight.Bold);Spacer(Modifier.height(8.dp));Text("Установка игры подключается следующим этапом",color=Color.LightGray);Spacer(Modifier.height(12.dp))
     Button(onClick={note="Механизм установки Minecraft ещё в разработке"},modifier=Modifier.fillMaxWidth()){Text("Установить Minecraft")}
     OutlinedButton(onClick={note="Запуск появится после интеграции игрового ядра"},modifier=Modifier.fillMaxWidth()){Text("Играть")}
    }}
    Spacer(Modifier.weight(1f));Text(note,color=Color(0xFF9BB6CC),fontSize=12.sp);Spacer(Modifier.height(8.dp));Text("GeCraft • независимый проект",color=Color.Gray,fontSize=11.sp)
   }
  }}
 }
}
