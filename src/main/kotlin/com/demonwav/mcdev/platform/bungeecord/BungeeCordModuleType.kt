/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.bungeecord

import com.demonwav.mcdev.asset.PlatformAssets
import com.demonwav.mcdev.platform.AbstractModuleType
import com.demonwav.mcdev.platform.PlatformType
import com.demonwav.mcdev.platform.bungeecord.generation.BungeeCordEventGenerationPanel
import com.demonwav.mcdev.platform.bungeecord.util.BungeeCordConstants
import com.demonwav.mcdev.util.CommonColors
import com.intellij.openapi.module.Module
import com.intellij.psi.PsiClass

object BungeeCordModuleType : AbstractModuleType<BungeeCordModule>("net.md-5", "bungeecord-api") {

    private const val ID = "BUNGEECORD_MODULE_TYPE"

    val IGNORED_ANNOTATIONS = listOf(BungeeCordConstants.HANDLER_ANNOTATION)
    val LISTENER_ANNOTATIONS = listOf(BungeeCordConstants.HANDLER_ANNOTATION)

    init {
        CommonColors.applyStandardColors(colorMap, BungeeCordConstants.CHAT_COLOR_CLASS)
    }

    override fun getPlatformType() = PlatformType.BUNGEECORD
    override fun getIcon() = PlatformAssets.BUNGEECORD_ICON
    override fun getId() = ID
    override fun getIgnoredAnnotations() = IGNORED_ANNOTATIONS
    override fun getListenerAnnotations() = LISTENER_ANNOTATIONS
    override fun generateModule(module: Module) = BungeeCordModule(module)
    override fun getEventGenerationPanel(chosenClass: PsiClass) = BungeeCordEventGenerationPanel(chosenClass)
}
