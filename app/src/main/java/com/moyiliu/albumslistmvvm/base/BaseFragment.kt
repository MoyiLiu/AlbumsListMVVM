package com.moyiliu.albumslistmvvm.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * Base implementation for all fragments in the application.
 */
abstract class BaseFragment() : Fragment() {

    /**
     * Returns the [BaseActivity] this fragment is currently associated with.
     *
     * May return `null` if the fragment is associated with a different `Context` instead.
     */
    val baseActivity: BaseActivity? get() = activity as? BaseActivity

    /**
     * Returns the [BaseActivity] this fragment is currently associated with.
     *
     * @throws IllegalStateException if not currently associated with a [BaseActivity].
     */
    fun requireBaseActivity(): BaseActivity =
        requireNotNull(baseActivity) { "Fragment $this not attached to a BaseActivity." }

    /**
     * Called when a fragment is first attached to its context.
     *
     * Performs dependency injection on the fragment.
     */
    @CallSuper
    override fun onAttach(context: Context) {
        injectDependencies()
        super.onAttach(context)
    }

    private fun injectDependencies() = AndroidSupportInjection.inject(this)

    /**
     * Creates data binding of type [T], initialized with the given [layout]
     * and the provided [init] function.
     *
     * The fragment becomes a lifecycle owner of the data binding.
     *
     * Example usage:
     * ```
     * override fun onCreateView(
     *     inflater: LayoutInflater,
     *     container: ViewGroup?,
     *     savedInstanceState: Bundle?
     * ): View? = bindContentView<LoginFormFragmentBinding>(
     *     inflater, R.layout.my_activity, container
     * ) {
     *     myViewModel = viewModel
     * }
     * ```
     */
    protected fun <T : ViewDataBinding> bindContentView(
        inflater: LayoutInflater,
        @LayoutRes layout: Int,
        container: ViewGroup?,
        init: T.() -> Unit
    ): View = DataBindingUtil.inflate<T>(inflater, layout, container, false)
        .apply {
            init()
            setLifecycleOwner(this@BaseFragment)
        }.root

}
