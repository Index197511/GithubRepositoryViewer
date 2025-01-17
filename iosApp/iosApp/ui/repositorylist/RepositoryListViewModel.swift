//
//  RepositoryListViewModel.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/13.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class RepositoryListViewModel : ObservableObject {
    let githubRepository: IGithubRepository
    let starredRepoRepository: IStarredRepoRepository
    
    @Published var state: DataState
    
    init(githubRepository: IGithubRepository, starredRepoRepository: IStarredRepoRepository) {
        self.githubRepository = githubRepository
        self.starredRepoRepository = starredRepoRepository
        self.state = DataState.Init()
    }
    
    func getRepositories() {
        githubRepository.getRepositories().collect(
            collector: Collector<DataState>{res in
                self.state = res
            },
            completionHandler: {(unit, err) in})
    }
    
    func starRepository(repository: shared.Repository) {
        starredRepoRepository.insertStarredRepo(repository: repository, completionHandler: {(unit, err) in})
    }
}
