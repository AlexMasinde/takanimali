package com.dca.takanimali.ui.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dca.takanimali.R

sealed class ProfileListItem(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val navLink: String
) {
    object Profile : ProfileListItem(R.drawable.profile_head, R.string.profile_head_text, "profile_details")
    object Collection :
        ProfileListItem(R.drawable.profile_collection, R.string.profile_collection_text, "history")

    object Password : ProfileListItem(R.drawable.profile_password, R.string.profile_password_text, "change_password")
    object Help : ProfileListItem(R.drawable.profile_help, R.string.profile_help_text, "help")
}