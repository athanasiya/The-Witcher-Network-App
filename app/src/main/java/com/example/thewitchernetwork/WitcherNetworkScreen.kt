package com.example.thewitchernetwork

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thewitchernetwork.data.DataSource
import com.example.thewitchernetwork.ui.FinishScreen
import com.example.thewitchernetwork.ui.SelectMonsterScreen
import com.example.thewitchernetwork.ui.SelectWitcherScreen
import com.example.thewitchernetwork.ui.StartScreen
import com.example.thewitchernetwork.ui.WitcherViewModel
import com.example.thewitchernetwork.ui.theme.LightBlue
import com.example.thewitchernetwork.ui.theme.TheWitcherNetworkTheme

enum class TheWitcherNetworkScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    SelectWitcher(title = R.string.hire_witcher),
    SelectMonster(title = R.string.select_problem),
    Final(title = R.string.final_screen),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WitcherNetworkAppBar(
    currentScreen: TheWitcherNetworkScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        colors = centerAlignedTopAppBarColors(
            containerColor = LightBlue
        ),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheWitcherNetworkApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = TheWitcherNetworkScreen.valueOf(
        backStackEntry?.destination?.route ?: TheWitcherNetworkScreen.Start.name
    )

    // Create ViewModel
    val viewModel: WitcherViewModel = viewModel()

    Scaffold(
        topBar = {
            WitcherNetworkAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() } // navigate back to the previous destination in the navigation stack
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TheWitcherNetworkScreen.Start.name,
        ) {
            composable(route = TheWitcherNetworkScreen.Start.name) {
                StartScreen(
                    onHireWitcherButtonClicked = {
                        navController.navigate(TheWitcherNetworkScreen.SelectWitcher.name)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            composable(route = TheWitcherNetworkScreen.SelectWitcher.name) {
                SelectWitcherScreen(
                    options = DataSource.witchers,
                    onAbortButtonClicked = {
                        navController.popBackStack(TheWitcherNetworkScreen.Start.name, inclusive = false)
                    },
                    onContinueButtonClicked = {
                        navController.navigate(TheWitcherNetworkScreen.SelectMonster.name)
                    },
                    onSelectionChanged = { witcher ->
                        viewModel.updateWitcher(witcher)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState()).padding(innerPadding)
                )
            }
            composable(route = TheWitcherNetworkScreen.SelectMonster.name) {
                SelectMonsterScreen(
                    options = DataSource.monsters,
                    onAbortButtonClicked = {
                        navController.popBackStack(TheWitcherNetworkScreen.Start.name, inclusive = false)
                    },
                    onContinueButtonClicked = {
                        navController.navigate(TheWitcherNetworkScreen.Final.name)
                    },
                    onSelectionChanged = { monster ->
                        viewModel.updateMonster(monster)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState()).padding(innerPadding)
                )
            }
            composable(route = TheWitcherNetworkScreen.Final.name) {
                FinishScreen(
                    witcherUiState = uiState,
                    onAbortButtonClicked = {
                        navController.popBackStack(TheWitcherNetworkScreen.Start.name, inclusive = false)
                    },
                    onToosACoinButtonClicked = {
                        navController.popBackStack(TheWitcherNetworkScreen.Start.name, inclusive = false)
                    },
                )
            }
        }
    }
}