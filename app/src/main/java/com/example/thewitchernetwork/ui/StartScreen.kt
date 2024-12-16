package com.example.thewitchernetwork.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thewitchernetwork.R
import androidx.compose.material3.ButtonDefaults.buttonColors

@Composable
fun StartScreen(
    onHireWitcherButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black)
            .then(modifier) // combines multiple modifiers, ensures the incoming modifier is added without overriding or conflicting the existing ones.
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.witcher_medalion),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.8f) // 80% of the screen's width (1f for full width)
            )
            Button(
                onClick = onHireWitcherButtonClicked,
                colors = buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Black
                ),
                modifier = Modifier.widthIn(min = 250.dp)
            ) {
                Text(stringResource(R.string.hire_witcher))
            }
        }
    }
}

@Preview()
@Composable
fun StartScreenPreview() {
    StartScreen(
        onHireWitcherButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .fillMaxSize()
    )
}