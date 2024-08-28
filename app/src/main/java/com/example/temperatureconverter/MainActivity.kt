package com.example.temperatureconverter

import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}


@Composable
fun Header(image: Int, description: String){
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ExampleTextField(){
    val text = remember { mutableStateOf("") }
    TextField(value = text.value, onValueChange = {text.value = it},
        label = {Text("What is your name?")})
}
@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit) {
    TextField(
        value = temperature,
        label = { Text("Enter a temperature in Celsius") },
        onValueChange = changed,
        modifier =Modifier.fillMaxWidth())
}
@Composable
fun TemperatureText(celsius: Int){
    val fahrenheit = (celsius.toDouble()*9/5)+32
    Text("$celsius Celsius is $fahrenheit Fahrenheit")
}

@Composable
fun MainActivityContent(){
    val celsius = remember {
        mutableStateOf(0)
    }
    val newCelsius = remember { mutableStateOf("")    }

    Column {
        Header(R.drawable.raise,"Sunrise image")
        EnterTemperature(newCelsius.value) {newCelsius.value = it}
        ConvertButton {
            newCelsius.value.toIntOrNull()?.let {
                celsius.value = 20
            } }
        TemperatureText(celsius.value)
    }
}

@Composable
fun ConvertButton(clicked: ()-> Unit){
    Button(onClick = clicked) {
        Text(text = "Convert")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity(){
    MainActivityContent()

}
