package com.kinzlstanislav.playground.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Without defining @ComponentScan("package.name") it will scan the current package hierarchy
 * for dependencies.
 * */
@Module
@ComponentScan("com.kinzlstanislav.playground.app.helpers")
class HelpersModule