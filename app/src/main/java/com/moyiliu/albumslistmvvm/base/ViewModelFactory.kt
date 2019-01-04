package com.moyiliu.albumslistmvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
open class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun <T : ViewModel?> create(type: Class<T>): T {
        val viewModel = viewModels[type]?.get()

        checkNotNull(viewModel) {
            """Error creating ${type.simpleName}

                No provider was found for type: `${type.name}`
            """.trimIndent()
        }

        check(type.isInstance(viewModel)) {
            """Error creating ${type.simpleName}

                Expected an instance of: `${type.name}`
                but got: `${viewModel.javaClass.name}`
            """.trimIndent()
        }
        return type.cast(viewModel)
    }

}