package com.dicoding.picodiploma.submission

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail.*

class DetailActivity : AppCompatActivity(){
    companion object {
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        val git = intent.getParcelableExtra<Github>(EXTRA_USER) as Github

        val avatar: ImageView =findViewById(R.id.avatar)
        val dataUsername:TextView=findViewById(R.id.username)
        val dataName:TextView=findViewById(R.id.name)
        val dataLocation:TextView=findViewById(R.id.location)
        val dataCompany:TextView=findViewById(R.id.company)
        val dataRepository: TextView=findViewById(R.id.repository)
        val username = git.username
        val name = git.name
        val location = git.location
        val company = git.company
        val repository=git.repository
        dataUsername.text=username
        dataName.text=name
        dataLocation.text=location
        dataCompany.text=company
        dataRepository.text=repository
        Glide.with(this)
                .load(git.avatar)
                .into(avatar)
        val sectionsPagerAdapter=SectionsPagerAdapter(this,supportFragmentManager)
        view_pager.adapter=sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation=0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}