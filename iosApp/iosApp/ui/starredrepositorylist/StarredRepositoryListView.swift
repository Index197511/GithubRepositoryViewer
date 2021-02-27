//
//  StarredRepositoryListView.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct StarredRepositoryListView: View {
    @ObservedObject var viewModel: StarredRepoRepositoryListViewModel
    
    init(viewModel: StarredRepoRepositoryListViewModel) {
        self.viewModel = viewModel
    }
    
    @ViewBuilder
    var body: some View {
        switch self.viewModel.state {
        case is DataState.Init:
            InitView().onAppear{
                viewModel.getStarredRepositories()
            }
        case is DataState.Success:
            List {
                ForEach((self.viewModel.state as! DataState.Success).data, id: \.self) { repository in
                    Repository(repository: repository, onClick: {repo in viewModel.unstarRepository(repository: repo)})
                }
            }
        case is DataState.Loading:
            LoadingView()
        case is DataState.Error:
            ErrorView(onClick: {self.viewModel.getStarredRepositories()})
        default:
            DefaultView()
        }
    }
}
