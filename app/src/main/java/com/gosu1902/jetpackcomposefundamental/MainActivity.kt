package com.gosu1902.jetpackcomposefundamental

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("debug", "setContent")
            val snackBarHostState = remember {
                SnackbarHostState()
            }
            var textFieldState by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            Scaffold(content = { padding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    TextField(value = textFieldState, onValueChange = {
                        textFieldState = it
                    }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    Button(onClick = {
                        scope.launch {
                            snackBarHostState.showSnackbar(message = "Hello $textFieldState", duration = SnackbarDuration.Short)
                        }
                    }) {
                        Text(text = "Pls greet me")
                    }
                }
            }, modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(hostState = snackBarHostState) })
        }
    }
}
