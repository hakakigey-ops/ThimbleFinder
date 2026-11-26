package com.example.thimblefinder

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*

/**
 * MAQUETTE. Aucune API / SDK / deep link officiel de 1xGames n'a été vérifié.
 * Donc : pas de requête réseau, pas de connexion, pas d'endpoint inventé.
 * L'app se limite à ouvrir la page officielle 1xBet dans le navigateur.
 */
class MainActivity : Activity() {

    // Seule URL utilisée : la page publique indiquée par l'utilisateur.
    private val officialUrl = "https://1xbet.com/en/mobile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pad = (16 * resources.displayMetrics.density).toInt()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad * 2, pad, pad)
        }
        val title = TextView(this).apply { text = "Rechercher un jeu 1xGames"; textSize = 22f }
        val input = EditText(this).apply {
            setText("Thimble"); hint = "Nom du jeu"; inputType = InputType.TYPE_CLASS_TEXT
        }
        val searchBtn = Button(this).apply { text = "Rechercher" }
        val result = TextView(this).apply { visibility = View.GONE; setPadding(0, pad, 0, pad) }
        val openBtn = Button(this).apply { text = "Ouvrir le site officiel 1xBet"; visibility = View.GONE }
        val loginBtn = Button(this).apply { text = "Se connecter (indisponible)"; isEnabled = false }
        val note = TextView(this).apply {
            text = "Connexion et synchronisation directes : impossibles sans API ou " +
                "autorisation officielle de 1xBet. Cette app ne reçoit ni ne stocke aucun mot de passe."
            textSize = 12f
        }

        searchBtn.setOnClickListener {
            val q = input.text.toString().trim()
            if (q.isEmpty()) { input.error = "Saisis un nom"; return@setOnClickListener }
            result.text = "« $q » : le catalogue 1xGames n'est pas accessible depuis cette app " +
                "(aucune interface officielle vérifiée). Cherche le jeu dans la rubrique 1xGames " +
                "du site ou de l'application officielle."
            result.visibility = View.VISIBLE
            openBtn.visibility = View.VISIBLE
        }
        openBtn.setOnClickListener {
            try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(officialUrl))) }
            catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Aucun navigateur disponible", Toast.LENGTH_SHORT).show()
            }
        }

        listOf(title, input, searchBtn, result, openBtn, loginBtn, note).forEach { root.addView(it) }
        setContentView(ScrollView(this).apply { addView(root) })
    }
}
