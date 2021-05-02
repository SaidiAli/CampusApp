package com.devhub.campus.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.devhub.campus.ui.main.MainSection

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    content()
}

@Composable
fun MainBottomNavigation(
    sections: List<MainSection>,
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

        sections.forEach { section ->
            BottomNavigationItem(
                icon = { Icon(imageVector = section.icon, contentDescription = "") },
                label = { Text(stringResource(section.label)) },
                alwaysShowLabel = false,
                selected = currentRoute == section.route,
                onClick = {
                    navController.navigate(route = section.route) {
                        popUpTo(currentRoute.orEmpty()) { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun Center(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Box(modifier = modifier.fillMaxSize().wrapContentSize()) {
        content()
    }
}
