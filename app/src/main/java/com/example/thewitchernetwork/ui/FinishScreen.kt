package com.example.thewitchernetwork.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thewitchernetwork.R
import com.example.thewitchernetwork.data.DataSource
import com.example.thewitchernetwork.model.Monster
import com.example.thewitchernetwork.model.Witcher
import com.example.thewitchernetwork.model.WitcherUiState

@Composable
fun FinishScreen(
    witcherUiState: WitcherUiState,
    onAbortButtonClicked: () -> Unit,
    onToosACoinButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.final_screen),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.selected_witcher),
        )
        Witcher(witcher = witcherUiState.witcher, modifier = Modifier.fillMaxWidth())

        Text(
            text = stringResource(R.string.selected_monster),
        )
        Monster(monster = witcherUiState.monster, modifier = Modifier.fillMaxWidth())
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onToosACoinButtonClicked
            ) {
                Text(stringResource(R.string.toss))
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_medium)),
                onClick = onAbortButtonClicked
            ) {
                Text(stringResource(R.string.cancel).uppercase())
            }
        }
    }
}

@Composable
fun Witcher(
    witcher: Witcher?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            witcher?.name ?: "",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun Monster(
    monster: Monster?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            monster?.name ?: "",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FinishScreenPreview(){
    FinishScreen(
        witcherUiState = WitcherUiState(
            witcher = DataSource.witchers[0],
            monster = DataSource.monsters[0],
        ),
        onAbortButtonClicked = {},
        onToosACoinButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .fillMaxSize()
    )
}