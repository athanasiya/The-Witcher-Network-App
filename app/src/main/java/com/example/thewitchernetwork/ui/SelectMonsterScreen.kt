package com.example.thewitchernetwork.ui

import com.example.thewitchernetwork.model.Monster

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.thewitchernetwork.R
import com.example.thewitchernetwork.data.DataSource
import com.example.thewitchernetwork.model.Witcher

@Composable
fun SelectMonsterScreen (
    options: List<Monster>,
    onAbortButtonClicked: () -> Unit,
    onContinueButtonClicked: () -> Unit,
    onSelectionChanged: (Monster) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { monster ->
            val onClick = {
                selectedValue = monster.name
                onSelectionChanged(monster)
            }
            MenuItemRow(
                monster = monster,
                selectedValue = selectedValue,
                onClick = onClick,
                modifier = Modifier.selectable(
                    selected = selectedValue == monster.name,
                    onClick = onClick
                )
            )
        }

        MenuScreenButtonGroup(
            selectedValue = selectedValue,
            onCancelButtonClicked = onAbortButtonClicked,
            onNextButtonClicked = { onContinueButtonClicked() },
            modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun MenuItemRow(
    monster: Monster,
    selectedValue: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedValue == monster.name,
            onClick = onClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = monster.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = monster.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${monster.number}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedValue: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ){
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel).uppercase())
        }
        Button(
            modifier = Modifier.weight(1f),
            enabled = selectedValue.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectMonsterScreenPreview(){
    SelectMonsterScreen(
        options = DataSource.monsters,
        onAbortButtonClicked = {},
        onContinueButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}