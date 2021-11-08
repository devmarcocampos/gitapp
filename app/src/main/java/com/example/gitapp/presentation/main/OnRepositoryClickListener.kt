package com.example.gitapp.presentation.main

import com.example.gitapp.domain.entity.Repository

interface OnRepositoryClickListener {
    fun onRepositoryClicked(repository: Repository)
}