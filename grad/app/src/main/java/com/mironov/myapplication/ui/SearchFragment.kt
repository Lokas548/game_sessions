package com.mironov.myapplication.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.mironov.myapplication.R


data class Game(
    val id: Int,
    val name: String,
    var isChecked : Boolean
)

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    // Внутренний класс ViewHolder для хранения ссылок на элементы представления
    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameNameTextView: TextView = itemView.findViewById<TextView>(R.id.recycleGameName)
        val gameCheckBox: CheckBox = itemView.findViewById(R.id.gameNameCheckBox)
    }

    // Создание нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_game_item, parent, false)
        return GameViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.gameNameTextView.text = game.name
        holder.gameCheckBox.isChecked = game.isChecked

        holder.gameCheckBox.setOnCheckedChangeListener { _, isChecked ->
            game.isChecked = isChecked
        }

    }

    override fun getItemCount(): Int {
        return games.size
    }
}

private fun loadGamesFromAssets(context: Context, fileName: String): List<Game>? {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val jsonString = String(buffer, Charsets.UTF_8)

        val gson = Gson()
        val listType = object : TypeToken<List<Game>>() {}.type
        val games: List<Game> = gson.fromJson(jsonString, listType)
        print(games)

        games
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}



class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_bottom_sheet, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_recycler_view)
        val games = loadGamesFromAssets(requireContext(), "games_list.json")

        if (games != null) {
            val gameAdapter = GameAdapter(games)
            recyclerView.adapter = gameAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        return view
    }
}

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val navController = NavHostFragment.findNavController(this)
        val searchButton = root.findViewById<Button>(R.id.filterBtn)


        searchButton.setOnClickListener{
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(childFragmentManager,"filterMenu")

        }

        return root

    }


}
