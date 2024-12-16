package com.example.thewitchernetwork.ui

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
fun SelectWitcherScreen (
    options: List<Witcher>,
    onAbortButtonClicked: () -> Unit,
    onContinueButtonClicked: () -> Unit,
    onSelectionChanged: (Witcher) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { witcher ->
            val onClick = {
                selectedValue = witcher.name
                onSelectionChanged(witcher)
            }
            MenuItemRow(
                witcher = witcher,
                selectedValue = selectedValue,
                onClick = onClick,
                modifier = Modifier.selectable(
                    selected = selectedValue == witcher.name,
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
    witcher: Witcher,
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
            selected = selectedValue == witcher.name,
            onClick = onClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = witcher.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = witcher.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectWitcherScreenPreview(){
    SelectWitcherScreen(
        options = DataSource.witchers,
        onAbortButtonClicked = {},
        onContinueButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}