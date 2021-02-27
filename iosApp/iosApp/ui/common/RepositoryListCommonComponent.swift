//
//  CommonComponent.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/24.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct Repository: View {
    let repository: shared.Repository
    let onClick: (_ repository: shared.Repository) -> ()
    var body: some View {
        Button(action: {onClick(repository)}) {
            VStack(alignment: .leading, spacing: 0) {
                User(author: repository.author, avatarUrl: repository.avatarUrl, avatarLoader: AvatarLoader()).padding(.bottom, 4)
                if let description = repository.description_ {
                    RepositoryName(name: repository.name)
                        .padding(.bottom, 4)
                    Description(description: description)
                        .padding(.bottom, 8)
                } else {
                    RepositoryName(name: repository.name)
                        .padding(.bottom, 8)
                }
                HStack {
                    Language(language: repository.language)
                    Star(star: repository.stars)
                }
            }
        }
    }
}

struct User: View {
    let author: String
    let avatarUrl: String
    @ObservedObject var avatarLoader: AvatarLoader
    
    init(author: String, avatarUrl: String, avatarLoader: AvatarLoader) {
        self.author = author
        self.avatarUrl = avatarUrl
        self.avatarLoader = avatarLoader
        self.avatarLoader.load(url: avatarUrl)
    }
    
    var body: some View {
        HStack {
            Image(uiImage: avatarLoader.userAvatar)
                .resizable()
                .clipShape(Circle())
                .frame(width: 30, height: 30, alignment: .leading)
            Text(author)
        }
    }
}

struct RepositoryName: View {
    let name: String
    var body: some View {
        Text(name).font(.title2).bold()
    }
}

struct Description: View {
    let description: String
    var body: some View {
        Text(description)
            .lineLimit(2)
            .font(.subheadline)
    }
}

struct Language: View {
    let language: String
    var body: some View {
        HStack {
            Circle().foregroundColor(Color.red).fixedSize()
            Text(language).font(.subheadline).foregroundColor(.gray)
        }
    }
}

struct Star: View {
    let star: Int32
    var body: some View {
        Text("star: \(star)").font(.subheadline).foregroundColor(.gray)
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

