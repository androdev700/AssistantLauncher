package com.andro.assistantlauncher

import android.content.Context
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.content.pm.PackageManager
import android.content.ComponentName
import android.graphics.drawable.Icon


class AssistantTileService : TileService() {

    override fun onClick() {
        val tile = qsTile
        val sharedPreferences = applicationContext.getSharedPreferences("ASSISTANT_PREF", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getBoolean("IS_ENABLED", true)) {
            tile.state = Tile.STATE_INACTIVE
            tile.label = "Show Icon"
            tile.icon = Icon.createWithResource(this, R.drawable.ic_visibile_white_24dp)
            val packageManager = packageManager
            val componentName = ComponentName(
                this,
                com.andro.assistantlauncher.AssistantLauncherActivity::class.java
            )
            packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            editor.putBoolean("IS_ENABLED", false)
        } else {
            tile.state = Tile.STATE_INACTIVE
            tile.label = "Hide Icon"
            tile.icon = Icon.createWithResource(this, R.drawable.ic_visibility_off_white_24dp)
            val packageManager = packageManager
            val componentName = ComponentName(
                this,
                com.andro.assistantlauncher.AssistantLauncherActivity::class.java
            )
            packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            editor.putBoolean("IS_ENABLED", true)
        }
        editor.apply()
        tile.updateTile()
        super.onClick()
    }

    override fun onStartListening() {
        val tile = qsTile
        val sharedPreferences = applicationContext.getSharedPreferences("ASSISTANT_PREF", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("IS_ENABLED", true)) {
            tile.label = "Hide Icon"
            tile.icon = Icon.createWithResource(this, R.drawable.ic_visibility_off_white_24dp)
            tile.state = Tile.STATE_INACTIVE
        } else {
            tile.label = "Show Icon"
            tile.icon = Icon.createWithResource(this, R.drawable.ic_visibile_white_24dp)
            tile.state = Tile.STATE_INACTIVE
        }
        tile.updateTile()
        super.onStartListening()
    }
}