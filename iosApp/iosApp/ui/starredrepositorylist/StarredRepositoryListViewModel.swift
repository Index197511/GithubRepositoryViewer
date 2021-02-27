//
//  StarredRepositoryListViewModel.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class StarredRepoRepositoryListViewModel : ObservableObject {
    let starRepoRepository: IStarredRepoRepository
    @Published var state: DataState
    
    init(repository: IStarredRepoRepository) {
        self.starRepoRepository = repository
        self.state = DataState.Init()
    }
    
    func getStarredRepositories() {
        starRepoRepository.getAllStarredRepo().collect(
            collector: Collector<[shared.Repository]>{res in
                self.state = DataState.Success(data: res)
                
            },
            completionHandler: {(unit, err) in})
    }
    
    func unstarRepository(repository: shared.Repository) {
        starRepoRepository.removeStarredRepo(id: repository.id, completionHandler: {(unit, err) in})
    }
}
