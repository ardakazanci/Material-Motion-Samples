package com.example.sergiobelda.materialmotion.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sergiobelda.materialmotion.R
import com.example.sergiobelda.materialmotion.databinding.AlbumsFragmentBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough

class AlbumsFragment : Fragment() {
    private var _binding: AlbumsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialFadeThrough.create()
        _binding = AlbumsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AlbumsAdapter(albums, requireContext())
        adapter.albumClickListener = object : AlbumsAdapter.AlbumClickListener {
            override fun onAlbumClick(id: Int, cardView: MaterialCardView) {
                val extras = FragmentNavigatorExtras(
                    cardView to id.toString()
                )
                val action = AlbumsFragmentDirections.navToAlbumFragment(id)
                findNavController().navigate(action, extras)
            }
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }
}