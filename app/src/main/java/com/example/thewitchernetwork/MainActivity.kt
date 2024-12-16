package com.example.thewitchernetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thewitchernetwork.ui.theme.TheWitcherNetworkTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheWitcherNetworkTheme {
                val windowSize = calculateWindowSizeClass(this)
                TheWitcherNetworkApp(
                    windowSize = windowSize.widthSizeClass,
                )
            }
        }
    }
}

@Preview
@Composable
fun TheWitcherNetworkAppCompactPreview() {
    TheWitcherNetworkTheme {
        Surface {
            TheWitcherNetworkApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun TheWitcherNetworkAppMediumPreview() {
    TheWitcherNetworkTheme {
        Surface {
            TheWitcherNetworkApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun TheWitcherNetworkAppExpandedPreview() {
    TheWitcherNetworkTheme {
        Surface {
            TheWitcherNetworkApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
    }
}