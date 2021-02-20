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
    let repository: IGithubRepository
    @Published var state: DataState

    init(repository: IGithubRepository) {
        self.repository = repository
        self.state = DataState.Empty()
    }
    
    func getRepositories() {
        repository.getRepositories().collect(collector: Collector<DataState>{res in
            print(res)
            self.state = res
        }, completionHandler: {(unit, err) in })
    }
}


class AvatarLoader: ObservableObject {
    @Published var userAvatar = UIImage()
    
    func load(url: String) {
        let session = URLSession(configuration: .default)
        let task = session.dataTask(with: URL(string: url)!) { data, _, _ in
            guard let image = data,
                  let imageFromRemote = UIImage(data: image)
            else {return}
            
            DispatchQueue.main.async {
                self.userAvatar = imageFromRemote
            }
            session.invalidateAndCancel()
        }
        task.resume()
    }
}
