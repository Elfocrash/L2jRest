package com.elfoworks.ioc

import com.elfoworks.services.PlayerService
import com.elfoworks.services.PlayerServiceImpl
import org.koin.dsl.module

val iocModule = module { 
    single<PlayerService> { PlayerServiceImpl() }
}