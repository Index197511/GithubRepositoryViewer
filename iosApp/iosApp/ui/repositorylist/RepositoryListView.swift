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
        self.viewModel.getRepositories()
    }
    
    var body: some View {
        switch self.viewModel.state {
        case is DataState.Empty:
            EmptyView()
        case is DataState.Success:
            List {
                ForEach((self.viewModel.state as! DataState.Success).data, id: \.self) { repository in
                    RepositoryListItem(repository: repository)
                }
            }
        case is DataState.Loading:
            LoadingView()
        case is DataState.Error:
            ErrorView(onClick: {self.viewModel.getRepositories()})
        default:
            EmptyView()
        }
    }
}

struct LoadingView: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorView: View {
    let onClick: () ->Void
    var body: some View {
        VStack(spacing: 0) {
            Text("Oops, an error occurred").foregroundColor(.gray).padding(.bottom, 4)
            Text("Please, try again").foregroundColor(.gray).padding(.bottom, 8)
            Button(action: onClick) {
                Text("RETRY")
            }
        }
    }
}


struct RepositoryListItem: View {
    let repository: Repository
    var body: some View {
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

struct RepositoryListItem_Preview: PreviewProvider {
    static var repositories: [Repository] = [
        Repository(author: "Index197511", avatarUrl: "https:.....", name: "TestRepository", url: "https://github.....", description: "This is Test", language: "Kotlin", stars: 12),
        Repository(author: "Index197511", avatarUrl: "https:.....", name: "TestRepository", url: "https://github.....", description: nil, language: "Kotlin", stars: 12),
        Repository(author: "Index197511", avatarUrl: "https:.....", name: "TestRepository", url: "https://github.....", description: "This is Test", language: "Kotlin", stars: 12)
    ]
    
    static var previews: some View {
        List {
            ForEach(repositories, id: \.self) { repo in
                RepositoryListItem(repository: repo)
            }
        }
    }
}
