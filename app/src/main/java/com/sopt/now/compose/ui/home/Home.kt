package com.sopt.now.compose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.component.bottomnavigation.BottomNavigation
import com.sopt.now.compose.component.bottomnavigation.BottomNavigationItem
import com.sopt.now.compose.component.text.TextWithTitle
import com.sopt.now.compose.component.toastMessage
import com.sopt.now.data.model.User
import com.sopt.now.data.model.UserViewModel

@Composable
fun Home(navHostController: NavHostController, viewModel: UserViewModel) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val authState by viewModel.liveData.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        authState?.let { state ->
            when (state) {
                is UiState.Loading -> {
                }

                is UiState.Success -> {
                    viewModel.setMyProfile(state.data)
                }

                is UiState.Error -> {
                    context.toastMessage(state.errorMessage)
                }
            }
        }
    }
    viewModel.getInfo(viewModel.myInfo.value.userid)

    val items = listOf(
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

    Scaffold(
        bottomBar = { BottomNavigation(selectedItem, { selectedItem = it }, items) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (selectedItem) {
                0 -> HomeScreen(viewModel)
                1 -> SearchScreen()
                2 -> MyProfileScreen(viewModel.myInfo.value)
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: UserViewModel) {
    LazyColumn {
        item {
            HomeMyProfile(data = viewModel.myProfile.value)
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(color = Color.Black)
            )
        }

        items(viewModel.userData) { index ->
            HomeFriendsProfile(data = index)
        }
    }
}

@Composable
fun SearchScreen() {
    //todo 추후 추가예정
}

@Composable
fun MyProfileScreen(myInfo: User) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.main_title).format(myInfo.nickname),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextWithTitle(
            title = stringResource(id = R.string.all_id),
            contents = myInfo.id
        )
        TextWithTitle(
            title = stringResource(id = R.string.all_password),
            contents = myInfo.password
        )
        TextWithTitle(
            title = stringResource(id = R.string.all_number),
            contents = myInfo.phonenumber
        )
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navCtrl = rememberNavController()
    val viewModel = UserViewModel()
    Home(navCtrl, viewModel = viewModel)
}

