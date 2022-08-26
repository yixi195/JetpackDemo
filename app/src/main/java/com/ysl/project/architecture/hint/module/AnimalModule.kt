package com.ysl.project.architecture.hint.module

import com.ysl.project.architecture.hint.Animal
import com.ysl.project.architecture.hint.Dog
import com.ysl.project.architecture.hint.Snake
import com.ysl.project.architecture.hint.qualifier.BindDog
import com.ysl.project.architecture.hint.qualifier.BindSnake
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 *  Craete by YangShlai on 2021/6/10
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class AnimalModule {

    @BindDog
    @Binds
    abstract fun bindDog(dog: Dog): Animal

    @BindSnake
    @Binds
    abstract fun bindSnake(snake : Snake) : Animal
}
