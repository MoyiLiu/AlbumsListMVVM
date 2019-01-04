package com.moyiliu.albumslistmvvm.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import com.moyiliu.albumslistmvvm.R
import dagger.android.AndroidInjection
import dagger.android.HasActivityInjector

/**
 * Base implementation for all activities in the application.
 *
 * @property withActionBar Indicates whether this activity uses its own `ActionBar`
 * and therefore should not use `ActionBar` provided by fragments.
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.
     *
     * Performs dependency injection on the activity.
     */
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    private fun injectDependencies() {
        if (applicationContext is HasActivityInjector)
            AndroidInjection.inject(this)
    }

    /**
     * Returns data binding of type [T], initialized with the given [layout]
     * and the provided [init] function.
     *
     * The activity becomes a lifecycle owner of the data binding.
     *
     * Example usage:
     * ```
     * bindContentView<MyActivityBinding>(R.layout.my_activity) {
     *     myViewModel = viewModel
     * }
     * ```
     */
    protected fun <T : ViewDataBinding> bindContentView(
        @LayoutRes layout: Int,
        init: T.() -> Unit
    ): T =
        DataBindingUtil.setContentView<T>(this, layout)
            .apply {
                init()
                setLifecycleOwner(this@BaseActivity)
            }

    protected fun showSnackBar(message: String, layoutId: Int) {
        findViewById<View>(layoutId)?.also { view ->
            Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .apply {
                    setAction(getString(R.string.dismiss)) {
                        dismiss()
                    }
                }.show()
        }
    }
}
