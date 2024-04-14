package com.example.greetingcard

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val explicitIntent = Intent(this, MSEChallengesActivity::class.java)
                    val implicitIntent = Intent(Intent.ACTION_SEND).apply{
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, resources.getStringArray(R.array.challenges_array))
                    }
                    val cameraActivityIntent = Intent(this, CaptureImageActivity::class.java)
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Allan Pirillis 1011108",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(24.dp)
                        )
                        Button(
                            onClick = {
                                try{
                                    startActivity(explicitIntent)
                                } catch (e: ActivityNotFoundException){
                                    e.printStackTrace()
                                }
                            },
                            Modifier.padding(12.dp)
                        ) {
                            Text(
                                text = "Start Activity Explicitly",
                                textAlign = TextAlign.Center
                            )
                        }
                        Button(
                            onClick = {
                                if (implicitIntent.resolveActivity(packageManager) != null){
                                    startActivity(implicitIntent)
                                }
                                      },
                            Modifier.padding(12.dp)
                        ) {
                            Text(
                                text = "Start Activity Implicitly",
                                textAlign = TextAlign.Center
                            )
                        }
                        Button(
                            onClick = {
                                try{
                                    startActivity(cameraActivityIntent)
                                } catch (e: ActivityNotFoundException){
                                    e.printStackTrace()
                                }
                            },
                            Modifier.padding(12.dp)
                        ) {
                            Text(
                                text = "View Image Activity",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {}
}