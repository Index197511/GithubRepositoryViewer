//
//  RepositoryListViewModel.swift
//  iosApp
//
//  Created by admin on 2021/02/13.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import SwiftUI

class RepositoryListViewModel : ObservableObject {
    let repository: IGithubRepository
    private var cancellable: AnyCancellable? = nil
    @Published var state: DataState

    init() {
        repository = GithubRepository(service: GithubService())
        state = DataState.Empty()
    }
    
    func getRepositories() {
        repository.getTrendRepositories().collect(collector: Collector<DataState>{res in
            print(res)
            self.state = res
        }, completionHandler: {(unit, err) in })
    }
}

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {
    let callback: (T) -> Void
    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        callback(value as! T)
        completionHandler(KotlinUnit(), nil)
    }
}

class AvatarLoader: ObservableObject {
    @Published var userAvatar = UIImage(systemName: "photo")!
    
    func loadImage(url: String) {
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
