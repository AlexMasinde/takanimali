package com.dca.takanimali.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dca.takanimali.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val navRoute: String
) {
    object Home : BottomNavItem(
        R.string.home_nav_title,
        R.drawable.home,
        R.drawable.home_selected,
        ROUTE_HOME
    )

    object Collection : BottomNavItem(
        R.string.collection_nav_title,
        R.drawable.collection,
        R.drawable.collection_selected,
        ROUTE_COLLECTION
    )

    object Points : BottomNavItem(
        R.string.reward_nav_title,
        R.drawable.points,
        R.drawable.points_selected,
        ROUTE_POINTS
    )

   object Profile: BottomNavItem(
       R.string.profile_nav_title,
       R.drawable.profile,
       R.drawable.profile_selected,
       ROUTE_PROFILE
   )
}
