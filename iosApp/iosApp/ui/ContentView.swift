//
//  BottomNavigationView.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ContentView: View {
    let repositoryListViewModel: RepositoryListViewModel
    let starredRepoRepositoryViewModel: StarredRepoRepositoryListViewModel
    let repositoryListView: RepositoryListView
    let starredRepositoryListView: StarredRepositoryListView
    
    init(starredRepoRepositoryListViewModel: StarredRepoRepositoryListViewModel,
         repositoryListViewModel: RepositoryListViewModel) {
        self.starredRepoRepositoryViewModel = starredRepoRepositoryListViewModel
        self.repositoryListViewModel = repositoryListViewModel
        self.repositoryListView = RepositoryListView(viewModel: repositoryListViewModel)
        self.starredRepositoryListView = StarredRepositoryListView(viewModel: starredRepoRepositoryListViewModel)
    }
    
    var body: some View {
        TabView {
            repositoryListView
                .tabItem{
                    VStack {
                        Image(systemName: "house.fill")
                        Text("Home")
                    }
                }
            starredRepositoryListView
                .tabItem{
                    VStack {
                        Image(systemName: "star.fill")
                        Text("Star")
                    }
                }
        }
    }
}
