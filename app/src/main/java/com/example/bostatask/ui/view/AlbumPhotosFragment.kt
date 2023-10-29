package com.example.bostatask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bostatask.databinding.FragmentAlbumPhotosBinding
import com.example.bostatask.model.response.ApiResponse
import com.example.bostatask.ui.adapter.PhotosAdapter
import com.example.bostatask.ui.viewModel.ActivityViewModel
import com.example.bostatask.common.OnClickListener
import com.example.bostatask.utils.UiUtils
class AlbumPhotosFragment : Fragment() {
    private lateinit var binding: FragmentAlbumPhotosBinding
    private val sharedViewModel by activityViewModels<ActivityViewModel>()
    private lateinit var photosAdapter: PhotosAdapter
    private var albumId: Int = 0
    private var albumTitle: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumPhotosBinding.inflate(inflater, container, false)
        getArgs()
        setAlbumTitle()
        setObservers()
        initAdapter()
        setRecyclerView()
        sharedViewModel.getAlbumPhotos(albumId)
        setSearchViewChangeListener()
        return binding.root
    }

    private fun setObservers() {
        with(sharedViewModel) {
            albumPhotosResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        binding.loadingView.visibility = View.GONE
                        albumPhotos = response.data
                        photosAdapter.submitList(response.data)
                    }

                    is ApiResponse.OnError -> {
                        binding.loadingView.visibility = View.GONE
                        UiUtils.showErrorMessage(requireContext())
                    }

                    is ApiResponse.IsLoading -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun getArgs() {
        val args: AlbumPhotosFragmentArgs by navArgs()
        albumId = args.albumId
        albumTitle = args.albumTitle
    }

    private fun setAlbumTitle() {
        (activity as? MainActivity)?.supportActionBar?.title = albumTitle
    }

    private fun initAdapter() {
        if (this::photosAdapter.isInitialized) return

        photosAdapter = PhotosAdapter(OnClickListener { _ ->
            //TODO show the Image viewer dialog of clicked image
        })
    }

    private fun setRecyclerView() {
        binding.photosRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
            adapter = photosAdapter
        }
    }

    private fun setSearchViewChangeListener() {
        binding.searchPhotosView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                UiUtils.hideKeyboard(requireActivity())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterImagesWIthQuery(newText)
                return true
            }
        })
    }

    private fun filterImagesWIthQuery(query: String?) {
        if (query.isNullOrEmpty()) {
            photosAdapter.submitList(sharedViewModel.albumPhotos)
            return
        }

        val photosList = sharedViewModel.albumPhotos.filter { photo -> photo.title.contains(query) }
        photosAdapter.submitList(photosList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        sharedViewModel.albumPhotos = emptyList()
        sharedViewModel.albumPhotosResponse.value = ApiResponse.IsLoading
    }
}