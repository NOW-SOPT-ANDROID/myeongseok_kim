package com.sopt.now.compose.component.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun BottomNavigation(
    selectedItem: Int,
    onClick: (Int) -> Unit,
    items: List<BottomNavigationItem>
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { onClick(index) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    NOWSOPTAndroidTheme {
        BottomNavigation(
            1, {}, listOf(
                BottomNavigationItem(
                    icon = Icons.Filled.Home,
                    label = stringResource(id = R.string.main_home)
                ),
                BottomNavigationItem(
                    icon = Icons.Filled.Search,
                    label = stringResource(id = R.string.main_search)
                ),
                BottomNavigationItem(
                    icon = Icons.Filled.Person,
                    label = stringResource(id = R.string.main_profile)
                )
            )
        )
    }

}