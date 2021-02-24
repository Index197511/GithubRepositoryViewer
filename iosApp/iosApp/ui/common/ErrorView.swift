//
//  ErrorView.swift
//  iosApp
//
//  Created by Index197511 on 2021/02/24.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

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
