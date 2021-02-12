//
//  RepositoriListViewModel.swift
//  iosApp
//
//  Created by admin on 2021/02/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class RepositoryListViewModel : ObservableObject {
    @Published var repos: DataState<Array<Repository>> = DataStateEmpty
}
