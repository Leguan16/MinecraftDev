/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2018 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.i18n.reference

import com.demonwav.mcdev.i18n.index.TranslationIndex
import com.demonwav.mcdev.i18n.index.TranslationInverseIndex
import com.demonwav.mcdev.i18n.index.merge
import com.demonwav.mcdev.util.mapToArray
import com.demonwav.mcdev.util.toTypedArray
import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.indexing.FileBasedIndex

class I18nGotoSymbolContributor : ChooseByNameContributor {
    override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String> {
        val scope = if (includeNonProjectItems) GlobalSearchScope.allScope(project) else GlobalSearchScope.projectScope(project)
        val keys = FileBasedIndex.getInstance().getAllKeys(TranslationIndex.NAME, project)
        val entries = keys.asSequence().flatMap { FileBasedIndex.getInstance().getValues(TranslationIndex.NAME, it, scope).merge("").translations.asSequence() }
        return entries.map { it.key }.distinct().filter { it.isNotEmpty() }.toTypedArray()
    }

    override fun getItemsByName(name: String, pattern: String, project: Project, includeNonProjectItems: Boolean): Array<NavigationItem> {
        val scope = if (includeNonProjectItems) GlobalSearchScope.allScope(project) else GlobalSearchScope.projectScope(project)
        val elements = TranslationInverseIndex.findElements(name, scope)
        return elements.mapToArray { it as NavigationItem }
    }
}
