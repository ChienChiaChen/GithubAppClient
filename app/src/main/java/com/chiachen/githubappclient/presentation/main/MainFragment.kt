package com.chiachen.githubappclient.presentation.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.chiachen.githubappclient.R
import com.chiachen.githubappclient.presentation.base.BaseFragment
import com.chiachen.githubappclient.presentation.base.adapters.ItemAdapter
import com.chiachen.githubappclient.util.extension.*
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject


class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazyFast { viewModelProvider<MainFragmentViewModel>(viewModelFactory) }

    @Inject
    lateinit var adapter: ItemAdapter

    private var queryDisposable: Disposable? = null

    override fun onViewBound(view: View, savedInstanceState: Bundle?) {
        super.onViewBound(view, savedInstanceState)
        view.list.adapter = adapter
        view.list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context!!)
        view.list.setHasFixedSize(true)
        view.list.addLoadMoreListener(viewModel::loadMore)

        RxTextView.afterTextChangeEvents(view.editText)
            .map { it.view().text.isBlank() }
            .asLiveData()
            .subscribe(viewLifecycleOwner) { isEmpty ->
                view.clear.toggleVisibility(!isEmpty, true)
            }

        viewModel.dataSet.subscribe(viewLifecycleOwner) { response ->
            adapter.update(response.items)
            emptyStateText.toggleVisibility(response.items.isEmpty() && !response.isSame, true)
        }

    }

    override fun onResume() {
        super.onResume()

        clear.setOnClickListener { editText.setText("") }
        queryDisposable = RxTextView.afterTextChangeEvents(editText)
            .map { it.editable()!!.toString() }
            .switchMap { string -> return@switchMap Observable.just(string) }
            .filter { it.isBlank() || it.trim().length >= 2 }
            .subscribe({
                adapter.clear()
                viewModel.setNewQuery(it)
            }, Throwable::printStackTrace)
    }

    override fun onPause() {
        super.onPause()
        queryDisposable.unsubscribe()
        clear.setOnClickListener(null)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_main

}
