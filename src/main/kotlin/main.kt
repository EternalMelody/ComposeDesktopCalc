import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.math.BigInteger

fun main() = Window(title = "Modulo Calculator", size = IntSize(800,600)) {
    MaterialTheme {
        val modDividend = remember { mutableStateOf("") }
        val modDivisor = remember { mutableStateOf("") }
        val remainder = remember { mutableStateOf("") }

        val base = remember { mutableStateOf("") }
        val exponent = remember { mutableStateOf("") }
        val power = remember { mutableStateOf("") }

        val cong1 = remember { mutableStateOf("") }
        val cong2 = remember { mutableStateOf("") }
        val congN = remember { mutableStateOf("") }
        val congResult = remember { mutableStateOf("") }

        val vars = listOf(modDividend,modDivisor,remainder,base,exponent,power)
        Row{

            Column(Modifier.padding(8.dp)) {
                Card(Modifier.padding(8.dp), elevation = 8.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("BigInteger Mod")
                        NumericTextField(modDividend)
                        NumericTextField(modDivisor)
                        Button(onClick = {
                            remainder.value = (BigInteger(modDividend.value).mod(BigInteger(modDivisor.value)).toString())
                        }) { Text("Mod") }
                        NumericTextField(remainder)
                    }
                }
                Card(Modifier.padding(8.dp), elevation = 8.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("BigInteger Pow")
                        NumericTextField(base)
                        NumericTextField(exponent)
                        Button(onClick = {
                            power.value = BigInteger(base.value).pow(exponent.value.toInt()).toString()
                        }) {
                            Text("POW")
                        }
                        NumericTextField(power)
                    }
                }
                Card(Modifier.padding(8.dp), elevation = 8.dp) {
                    Button(onClick = {
                        vars.forEach { it.value = "" }
                    }) { Text("Clear All")}
                }
            }
            Column(Modifier.padding(8.dp)) {
                Card(Modifier.padding(8.dp), elevation = 8.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Congruence check")
                        NumericTextField(cong1) { congResult.value = "" }
                        NumericTextField(cong2) { congResult.value = "" }
                        NumericTextField(congN) { congResult.value = "" }
                        Button(onClick = {
                            congResult.value = (BigInteger(cong1.value).mod(BigInteger(congN.value)) == BigInteger(cong2.value).mod(BigInteger(congN.value))).toString()
                        }) {
                            Text("Check")
                        }
                        Text(congResult.value)
                    }
                }
            }
        }
    }
}

@Composable
fun NumericTextField(value: MutableState<String>, oVC: ()->Unit = {}) {
    TextField(value.value, onValueChange = {
        if (it.all { char -> char.isDigit() }) value.value = it
        oVC.invoke()
    })
}
