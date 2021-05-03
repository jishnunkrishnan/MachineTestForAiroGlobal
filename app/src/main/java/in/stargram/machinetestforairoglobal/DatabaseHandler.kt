package `in`.stargram.machinetestforairoglobal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DB_NAME = "ProjectDatabase"
val TABLE_NAME = "Prjects"
val COL_PROJECT_NAME = "name"
val COL_PROJECT_ID = "id"
val COL_ID = "primaryid"
val COL_DATE = "date"
val COL_DATE_EDITED = "editeddate"


class DatabaseHandler(var context: Context): SQLiteOpenHelper(context, DB_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_PROJECT_ID VARCHAR(256), $COL_PROJECT_NAME VARCHAR(256), $COL_DATE VARCHAR(256), $COL_DATE_EDITED VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addProject(projectModel: ProjectModel) {

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_PROJECT_ID, projectModel.projectId)
        cv.put(COL_PROJECT_NAME, projectModel.projectName)
        cv.put(COL_DATE, projectModel.date)
        cv.put(COL_DATE_EDITED, projectModel.edited_date)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Project Saved Successfully", Toast.LENGTH_SHORT).show()
    }

    fun showProjects (): MutableList<ProjectModel> {

        val db = this.readableDatabase
        val list: MutableList<ProjectModel> = ArrayList()
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {

            do {

                val projectData = ProjectModel()
                projectData.projectId = result.getString(result.getColumnIndex(COL_PROJECT_ID))
                projectData.projectName = result.getString(result.getColumnIndex(COL_PROJECT_NAME))
                projectData.date = result.getString(result.getColumnIndex(COL_DATE))
                projectData.edited_date = result.getString(result.getColumnIndex(COL_DATE_EDITED))
                list.add(projectData)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
}