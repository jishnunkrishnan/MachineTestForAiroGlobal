package `in`.stargram.machinetestforairoglobal.addProjectModule

import `in`.stargram.machinetestforairoglobal.DatabaseHandler
import `in`.stargram.machinetestforairoglobal.MainActivity
import `in`.stargram.machinetestforairoglobal.ProjectModel
import `in`.stargram.machinetestforairoglobal.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_project.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class AddProjectActivity : AppCompatActivity() {

     fun getDateTime(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)

        val date = getDateTime()
        Log.i("HelloDate", date.toString())
        btnSaveProject.setOnClickListener {

            if (tvProjectId.text.toString().isNotEmpty() && tvProjectName.text.toString().isNotEmpty()) {

                val db = DatabaseHandler(this)
                val addProject = ProjectModel(
                    tvProjectId.text.toString(),
                    tvProjectName.text.toString(),
                    date, date
                )
                db.addProject(addProject)
                startActivity(Intent(this, MainActivity::class.java))
            } else {

                Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}