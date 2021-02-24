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
    let repository: IStarredRepoRepository
    @Published var state: DataState
    
    init(repository: IStarredRepoRepository) {
        self.repository = repository
        self.state = DataState.Init()
    }
    
    func getStarredRepositories() {
        repository.getAllStarredRepo().collect(
            collector: Collector<[Repository]>{res in
                self.state = DataState.Success(data: res)
                
            },
            completionHandler: {(unit, err) in})
    }
}
