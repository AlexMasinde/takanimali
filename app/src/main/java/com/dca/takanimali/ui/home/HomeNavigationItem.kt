package com.dca.takanimali.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dca.takanimali.R

sealed class HomeNavigationItem(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val icon: Int,
    val navDestination: String
) {
    object Report : HomeNavigationItem(
        R.string.report_home_nav,
        R.string.report_subtitle,
        R.drawable.home_report,
        navDestination = "report"
    )
    object Redeem : HomeNavigationItem(
        R.string.redeem_home_nav,
        R.string.redeem_subtitle,
        R.drawable.home_points,
        navDestination = "points"
    )
    object Collect : HomeNavigationItem(
        R.string.collect_home_nav,
        R.string.collect_subtitle,
        R.drawable.home_collect,
        navDestination = "collect"
    )
    object Profile : HomeNavigationItem(
        R.string.profile_home_nav,
        R.string.profile_subtitle,
        R.drawable.home_profile,
        navDestination = "profile"
    )
    object History : HomeNavigationItem(
        R.string.history_home_nav,
        R.string.history_subtitle,
        R.drawable.home_data,
        navDestination = "history"
    )
    object Logout : HomeNavigationItem(
        R.string.logout_home_nav,
        R.string.logout_subtitle,
        R.drawable.home_logout,
        navDestination = "logout"
    )
    object Password : HomeNavigationItem(
        R.string.password_home_nav,
        R.string.password_subtitle,
        R.drawable.home_password,
        navDestination = "change_password"
    )

}