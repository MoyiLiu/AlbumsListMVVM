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
import androidx.navigation.NavController
import dagger.android.AndroidInjection

/**
 * Base implementation for all activities in the application.
 *
 * @property withActionBar Indicates whether this activity uses its own `ActionBar`
 * and therefore should not use `ActionBar` provided by fragments.
 */
abstract class BaseActivity(
    internal val withActionBar: Boolean = false
) : AppCompatActivity() {

    /** Main navigation controller associated with this Activity. */
//    abstract val navController: NavController

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

    private fun injectDependencies() = AndroidInjection.inject(this)

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

    /**
     * Sets a `Toolbar` with the given [toolbarId], defined in a fragment,
     * to act as the `ActionBar` for this Activity window.
     *
     * If [withActionBar] is set to `true`, this method will do nothing.
     *
     * @param toolbarId Id of the `Toolbar` to act as `ActionBar`.
     * Pass `null` to remove the currently set `ActionBar`.
     * @param init A function initializing the `ActionBar`.
     */
    fun setFragmentActionBar(@IdRes toolbarId: Int?, init: ActionBar.() -> Unit = {}) {
        if (withActionBar) {
            toolbarId?.let { findViewById<View>(it) }?.visibility = View.GONE
        } else {
            setSupportActionBar(toolbarId)
            supportActionBar?.init()
        }
    }

    /**
     * Sets a `Toolbar` with the given [toolbarId] to act as the `ActionBar`
     * for this Activity window.
     *
     * @param toolbarId Id of the `Toolbar` to act as `ActionBar`.
     * Pass `null` to remove the currently set `ActionBar`.
     */
    fun setSupportActionBar(@IdRes toolbarId: Int?) {
        setSupportActionBar(toolbarId?.let { findViewById<Toolbar>(it) })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
