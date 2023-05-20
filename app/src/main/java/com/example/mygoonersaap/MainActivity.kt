package com.example.mygoonersaap

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygoonersaap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPlayer.setHasFixedSize(true)
        list.addAll(getListPlayer())
        showRecyclerList()

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvPlayer.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvPlayer.layoutManager = LinearLayoutManager(this)
        }

        supportActionBar?.title = "Arsenal Player"
    }

    private fun showRecyclerList() {
        binding.rvPlayer.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        binding.rvPlayer.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickListener(object : ListPlayerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivityPlayer::class.java)
                intent.putExtra("key_player", list[position])
                startActivity(intent)
            }
        })
    }

    private fun getListPlayer(): Collection<Player> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listPlayer = ArrayList<Player>()
        for (i in dataName.indices) {
            val food = Player(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPlayer.add(food)
        }
        return listPlayer
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvPlayer.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvPlayer.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}