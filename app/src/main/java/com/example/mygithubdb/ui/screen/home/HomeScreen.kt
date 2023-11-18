package com.example.mygithubdb.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mygithubdb.di.Injection
import com.example.mygithubdb.model.ItemsItem
import com.example.mygithubdb.ui.ViewModelFactory
import com.example.mygithubdb.ui.components.SearchUserBar
import com.example.mygithubdb.ui.components.UserListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel:HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
    username: String,
) {
    LaunchedEffect(Unit, block = {
        viewModel.getUsers(username)
    })
    val githubListUser by viewModel.githubListUser.observeAsState(emptyList())
    val query by viewModel.query.collectAsState()
    HomeContent(
        modifier = modifier,
        githubUsers = githubListUser,
        navigateToDetail = navigateToDetail ,
        query = query,
        onQueryChange = { viewModel.search(it) }
    )
}

@Composable
fun HomeContent(
    githubUsers: List<ItemsItem>?,
    modifier: Modifier,
    navigateToDetail: (String) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    LazyColumn(modifier.fillMaxHeight()) {
        item {
            SearchUserBar(query = query, onQueryChange = onQueryChange)
        }
        if(githubUsers != null) {
            items(githubUsers) {data ->
                UserListItem(
                    name = data.login,
                    photoUrl = data.avatarUrl,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.login)
                    }
                )
            }
        }
    }
}
