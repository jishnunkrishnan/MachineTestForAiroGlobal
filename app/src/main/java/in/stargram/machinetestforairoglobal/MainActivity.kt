package `in`.stargram.machinetestforairoglobal

import `in`.stargram.machinetestforairoglobal.addProjectModule.AddProjectActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val db = DatabaseHandler(this)
        val data = db.showProjects()
        for (i in 0 until data.size) {

            Log.i("Hello", "++++ ${data[i].projectId} + ${data[i].projectName}, ${data[i].date}, ${data[i].edited_date}")
        }

        val ada = ProjectAdapter(data)
        rvProjectList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvProjectList.adapter = ada

        btnPlus.setOnClickListener {

            startActivity(Intent(this, AddProjectActivity::class.java))
        }
    }
}