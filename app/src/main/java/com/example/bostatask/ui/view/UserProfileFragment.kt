package com.example.bostatask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bostatask.databinding.FragmentUserProfileBinding
import com.example.bostatask.model.model.UserAddress
import com.example.bostatask.model.response.ApiResponse
import com.example.bostatask.ui.adapter.AlbumsAdapter
import com.example.bostatask.ui.viewModel.ActivityViewModel
import com.example.bostatask.common.OnClickListener
import com.example.bostatask.utils.UiUtils

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private val sharedViewModel by activityViewModels<ActivityViewModel>()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        setObservers()
        initAdapter()
        setRecyclerView()
        if (!sharedViewModel.userPersonalDataResponse.isInitialized) {
            sharedViewModel.getUserPersonalData()
        }
        return binding.root
    }

    private fun setObservers() {
        with(sharedViewModel) {
            userPersonalDataResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        binding.loadingView.visibility = View.GONE
                        binding.userNameTextView.text = response.data.name
                        binding.userAddressTextView.text = composeUserAddress(response.data.address)
                        sharedViewModel.getUserAlbums()
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

            userAlbumsResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        binding.loadingView.visibility = View.GONE
                        albumsAdapter.submitList(response.data)
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

    private fun initAdapter() {
        if (this::albumsAdapter.isInitialized) return

        albumsAdapter = AlbumsAdapter(OnClickListener { album ->
            val action =
                UserProfileFragmentDirections.actionUserAlbumsFragmentToAlbumPhotosFragment(
                    album.title,
                    album.id
                )
            findNavController().navigate(action)
        })
    }

    private fun setRecyclerView() {
        binding.albumsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = albumsAdapter
        }
    }

    private fun composeUserAddress(address: UserAddress): String {
        return "${address.street}, ${address.city}, ${address.suite}, ${address.zipCode}"
    }
}