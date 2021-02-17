//
//  Flow2Combine.swift
//  iosApp
//
//  Created by admin on 2021/02/14.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine

func createPublisher<T>(
    wrapper: KoruSuspendWrapper<T>
) -> AnyPublisher<T?, Error> {
    var job: Kotlinx_coroutines_coreJob? = nil
    return Deferred {
        Future<T?, Error> { promise in
            job = wrapper.subscribe(
                onSuccess: { item in promise(.success(item)) },
                onThrow: { error in promise(.failure(SharedError(error))) }
            )
        }.handleEvents(receiveCancel: { job?.cancel(cause: nil) })
    }.eraseToAnyPublisher()
}
