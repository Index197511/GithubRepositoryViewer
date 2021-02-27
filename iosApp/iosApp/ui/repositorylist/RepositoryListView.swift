//
//  RepositoryListVIew.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct RepositoryListView: View {
    @ObservedObject var viewModel: RepositoryListViewModel
    
    init(viewModel: RepositoryListViewModel) {
        self.viewModel = viewModel
    }
    
    @ViewBuilder
    var body: some View {
        switch self.viewModel.state {
        case is DataState.Init:
            InitView().onAppear{
                viewModel.getRepositories()
            }
        case is DataState.Success:
            List {
                ForEach((self.viewModel.state as! DataState.Success).data, id: \.self) { repository in
                    Repository(repository: repository) { repository in
                        viewModel.starRepository(repository: repository)
                    }
                }
            }
        case is DataState.Loading:
            LoadingView()
        case is DataState.Error:
            ErrorView(onClick: {self.viewModel.getRepositories()})
        default:
            DefaultView()
        }
    }
}
